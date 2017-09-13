package com.yili.schedule.core;

import com.alibaba.fastjson.JSON;
import com.yili.schedule.config.TaskInfo;
import com.yili.schedule.config.TaskJob;
import com.yili.schedule.config.TaskStatus;
import com.yili.schedule.config.ZookeeperProfile;
import com.yili.schedule.listener.TaskInfoListener;
import com.yili.schedule.listener.TaskListener;
import com.yili.schedule.task.TaskExecutor;
import com.yili.schedule.zk.ZkUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring的注入类
 * Created by lancey on 15/11/21.
 */
public class ScheduleSpringFactroy implements ApplicationContextAware, InitializingBean, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleSpringFactroy.class);

    private ApplicationContext applicationContext;
    private ZookeeperProfile zookeeperProfile;

    private CuratorFramework client;
    private ConcurrentHashMap<String, TaskInfo> taskInfoMap;
    private TaskInfoListener taskInfoListener;
    private ConcurrentHashMap<String, TaskExecutor> jobMap;
    private ConcurrentHashMap<String, TaskListener> taskListenerMap;
    private Map<String, LeaderLatch> leaderLatchMap = new HashMap<String, LeaderLatch>();
    private String uuid;
    private volatile boolean stopFlag = false;
    private String host;
    private int maxLogSize = 1000;
//    private


    private void init() {
        client = zookeeperProfile.createClient();
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        uuid = host + "$" + UUID.randomUUID().toString();

        loadTaskInfos();
        loadTaskJobs();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setZookeeperProfile(ZookeeperProfile zookeeperProfile) {
        this.zookeeperProfile = zookeeperProfile;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void loadTaskInfos() {
        try {
            List<TaskInfo> list = ZkUtils.getJobList(zookeeperProfile);
            taskInfoMap = new ConcurrentHashMap<>();
            for (TaskInfo taskInfo : list) {
                taskInfoMap.put(taskInfo.getBeanName(), taskInfo);
            }
            taskInfoListener = new TaskInfoListener(ZkUtils.getTaskInfoPath(zookeeperProfile));
            taskInfoListener.exec(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * job加载，在spring的环境当中通过bean.id加载
     */
    private void loadTaskJobs() {
        jobMap = new ConcurrentHashMap<>();
        taskListenerMap = new ConcurrentHashMap<>();
        for (TaskInfo key : taskInfoMap.values()) {
            startTaskJob(key);
        }
    }

    private void stopTaskJob(TaskInfo taskInfo) {
        String key = taskInfo.getBeanName();
        if (jobMap.containsKey(key)) {
            jobMap.get(key).close();
            jobMap.remove(key);
        }

        if (taskListenerMap.containsKey(key)) {
            try {
                taskListenerMap.get(key).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            taskListenerMap.remove(key);
        }

        if (leaderLatchMap.containsKey(key)) {
            try {
                leaderLatchMap.get(key).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            leaderLatchMap.remove(key);
        }

    }

    /**
     * 检查job是否在当前的机器上有权限运行
     * 首先检查是否在可运行的列表当中,如果没有配置忽略,如果存在就可以运行
     * 再检查不可运行列表，如果存在就拒绝
     *
     * @param taskInfo
     * @return
     */
    private boolean checkDenyHost(TaskInfo taskInfo) {
        if (StringUtils.isNotEmpty(taskInfo.getAllowHosts())) {
            String[] ips = taskInfo.getAllowHosts().split(",");
            for (String ip : ips) {
                if (StringUtils.equals(ip, host)) {
                    return false;
                }
            }
            return true;
        }
        if (StringUtils.isNotEmpty(taskInfo.getDenyHosts())) {
            String[] ips = taskInfo.getDenyHosts().split(",");
            for (String ip : ips) {
                if (StringUtils.equals(ip, host)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void startTaskJob(TaskInfo taskInfo) {
        if (TaskStatus.STOP.equals(taskInfo.getStatus())) {
            LOGGER.info("job:{} status:{}", taskInfo.getBeanName(), taskInfo.getStatus());
            return;
        }
        String beanName = taskInfo.getBeanName();
        if (!applicationContext.containsBean(beanName)) {
            LOGGER.info("job:{} don't exists", taskInfo);
        } else {
            Object bean = applicationContext.getBean(beanName);
            if (bean == null || bean.getClass().isAssignableFrom(TaskJob.class)) {
                LOGGER.info("job:{} don't has TaskJob.", beanName);
                return;
            }

            if (checkDenyHost(taskInfo)) {
                LOGGER.info("job:{} allow in ip:{} run.", beanName, host);
                return;
            }

            TaskJob taskJob = (TaskJob) bean;
            TaskExecutor taskExecutor = new TaskExecutor(this, taskInfoMap.get(beanName), taskJob);
            jobMap.put(beanName, taskExecutor);
            //向zk注册相应的节点
            //对应的节点在task节点下面
            String path = zookeeperProfile.makePath("task", beanName);
            if (!checkExists(path)) {
                try {
                    client.create().creatingParentsIfNeeded().forPath(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                if (!checkExists(ZKPaths.makePath(path, uuid))) {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(ZKPaths.makePath(path, uuid), beanName.getBytes());
                }
                TaskListener listener = new TaskListener(path);

                taskListenerMap.put(beanName, listener);

                listener.exec(this);

                LeaderLatch leader = new LeaderLatch(client, ZKPaths.makePath(path, "lock"), uuid);
                leader.start();
                leaderLatchMap.put(beanName, leader);
                Thread.sleep(2000);
                if (leader.hasLeadership()) {
                    LOGGER.info("task:{},UUID:{},is leader", taskInfo, uuid);
                    taskExecutor.init();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkExists(String path) {
        return ZkUtils.hasExistsPath(client, path);
    }


    /**
     * 任务移除
     *
     * @param path
     */
    public void handleTaskRemove(String path) {
        LOGGER.info("handleTaskRemove:{}", path);
        String beanName = getBeanNameByPath(path);
        //停掉选举操作
        if (leaderLatchMap.containsKey(beanName)) {
            try {
                leaderLatchMap.get(beanName).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            leaderLatchMap.remove(beanName);
        }

        //停止调度
        if (jobMap.containsKey(beanName)) {
            jobMap.get(beanName).close();
            jobMap.remove(beanName);
        }
        if (taskInfoMap.containsKey(beanName)) {
            taskInfoMap.remove(beanName);
        }
    }

    /**
     * 添加新的任务
     *
     * @param path
     */
    public void handleTaskAdd(String path) {
        LOGGER.info("handleTaskAdd:{}", path);
        String beanName = getBeanNameByPath(path);
        TaskInfo taskInfo = ZkUtils.getTaskInfo(zookeeperProfile, beanName);
        if (taskInfo != null) {
            TaskInfo t = taskInfoMap.putIfAbsent(beanName, taskInfo);
            if (t == null) {
                startTaskJob(taskInfo);
            }
        }
    }

    /**
     * 任务更新
     *
     * @param path
     */
    public void handleTaskUpdate(String path) {
        LOGGER.info("handleTaskUpdate:{}", path);
        String beanName = getBeanNameByPath(path);
        TaskInfo taskInfo = ZkUtils.getTaskInfo(zookeeperProfile, beanName);
        TaskInfo currentTaskInfo = taskInfoMap.get(beanName);
        if (currentTaskInfo == null) {
            LOGGER.info("job {} don't exists in app", beanName);
            return;
        }
        boolean updateFlag = false;
        boolean updateCronExpresessionFlag = false;
        boolean updateStatus = false;
        //taskInfo存在于当时的服务当中，再来比对更新的内容，如果内容不存在更新就忽略
        //更改过操作时间
        if (!StringUtils.equals(taskInfo.getCronExpression(), currentTaskInfo.getCronExpression())) {
            currentTaskInfo.setCronExpression(taskInfo.getCronExpression());
            updateCronExpresessionFlag = true;
            updateFlag = true;
        }

        if (!taskInfo.getStatus().equals(currentTaskInfo.getStatus())) {
            currentTaskInfo.setStatus(taskInfo.getStatus());
            updateStatus = true;
            updateFlag = true;
        }

        if (!StringUtils.equals(taskInfo.getConfig(), currentTaskInfo.getConfig())) {
            currentTaskInfo.setConfig(taskInfo.getConfig());
            updateFlag = true;
        }

        if (taskInfo.getMaxLimit() != currentTaskInfo.getMaxLimit()) {
            currentTaskInfo.setMaxLimit(taskInfo.getMaxLimit());
            updateFlag = true;
        }

        if (taskInfo.getThreads() != currentTaskInfo.getThreads()) {
            currentTaskInfo.setThreads(taskInfo.getThreads());
            updateFlag = true;
        }
        if (taskInfo.isConcurrency() != currentTaskInfo.isConcurrency()) {
            currentTaskInfo.setConcurrency(taskInfo.isConcurrency());
            updateFlag = true;
        }
        if (!StringUtils.equals(taskInfo.getAllowHosts(), currentTaskInfo.getAllowHosts())) {
            currentTaskInfo.setAllowHosts(taskInfo.getAllowHosts());
            updateFlag = true;
        }
        if (!StringUtils.equals(taskInfo.getDenyHosts(), currentTaskInfo.getDenyHosts())) {
            currentTaskInfo.setDenyHosts(taskInfo.getDenyHosts());
            updateFlag = true;
        }
        LOGGER.info("updateFlag:{},update content:{}", updateFlag, JSON.toJSONString(currentTaskInfo));
        if (updateFlag) {
            TaskExecutor taskExec = jobMap.get(beanName);
            if (taskExec != null) {
                if (updateCronExpresessionFlag) {
                    taskExec.updateCron();
                }
            }

            if (updateStatus) {
                if (currentTaskInfo.getStatus().equals(TaskStatus.RUN)) {
                    startTaskJob(currentTaskInfo);
                } else {
                    stopTaskJob(currentTaskInfo);
                }
            } else {
                if (jobMap.containsKey(beanName)) {
                    if (leaderLatchMap.get(beanName).hasLeadership()) {
                        LOGGER.debug("JOB:{} reinit", beanName);
                        taskExec.reinit();
                    }
                }
            }
        }
    }

    private String getBeanNameByPath(String path) {
        String[] arr = path.split("/");
        return arr[arr.length - 1];
    }

    /**
     * 发现主机退出
     *
     * @param path
     */
    public void handleHostRemove(String path) {
        if (stopFlag) {
            return;
        }
        LOGGER.info("handleHostRemove:{} uuid：{}", path, uuid);

        if (leaderLatchMap.get(path).hasLeadership()) {
            jobMap.get(path).init();
        }
    }

    public void stop() {
        if (!stopFlag) {
            stopFlag = true;
            for (String key : leaderLatchMap.keySet()) {
                try {
                    leaderLatchMap.get(key).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (TaskListener listener : taskListenerMap.values()) {
                try {
                    listener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                taskInfoListener.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (client != null) {
                client.close();
            }
        }
    }

    public void insertLog(String path, String log) {
        try {
            String fullPath = zookeeperProfile.makePath("log", path);
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                    .forPath(ZKPaths.makePath(fullPath, "log"), log.getBytes());

            if (maxLogSize > 0) {//删除超出的部分的2/3;保留最后的1/3
                List<String> logs = client.getChildren().forPath(fullPath);
                if (logs.size() > maxLogSize) {
                    Collections.sort(logs);
                    int len = maxLogSize * 2 / 3;
                    for (int i = 0; i < len; i++) {
                        client.delete().forPath(ZKPaths.makePath(fullPath, logs.get(i)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CuratorFramework getClient() {
        return client;
    }

    @Override
    public void destroy() throws Exception {
        this.stop();
    }

    public String getUuid() {
        return uuid;
    }
}
