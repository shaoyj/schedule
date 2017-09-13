package com.syj.schedule;

import com.alibaba.fastjson.JSONObject;
import com.syj.schedule.config.TaskInfo;
import com.syj.schedule.config.ZookeeperProfile;
import com.syj.schedule.zk.ZkUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.List;


public class ZkTest {

    //@Test
    public void testConnectZk()throws Exception{
        String rootNode="abcd1";
        CuratorFramework client = getCuratorFramework(rootNode);
        String path = ZKPaths.makePath("/",rootNode);
        System.out.println("path::::::"+path);
        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, "hahaha".getBytes());
//        client.getData().inBackground()
//        client.setData().forPath(path,"hahaha".getBytes());
        String str = new String(client.getData().watched().forPath(path));
        System.out.println(str);

        client.close();
    }

//    @Test
    public void testListener()throws Exception{
        String rootNode="/abcd2";
        CuratorFramework client = getCuratorFramework(rootNode);
        PathChildrenCacheListener listener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("path:"+pathChildrenCacheEvent);
            }
        };
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println(curatorEvent.getWatchedEvent());
                if(curatorEvent.getType()== CuratorEventType.SET_DATA){
                    System.out.println("set data");
                }
            }
        });
        PathChildrenCache cache = new PathChildrenCache(client,rootNode,false);
        cache.getListenable().addListener(listener);
        cache.start();
        client.checkExists().forPath(rootNode);
        //client.create().withMode(CreateMode.PERSISTENT).forPath(rootNode);
        client.setData().forPath(rootNode,"aaaa".getBytes());

        String node = ZKPaths.makePath(rootNode, "aaa");
        client.create().withMode(CreateMode.EPHEMERAL).forPath(node);
        client.setData().forPath(node,"11111123344".getBytes());
        Thread.sleep(1000*30);
        client.close();
    }

    private CuratorFramework getCuratorFramework(String rootNode) {
        String connect="localhost:2181";
        RetryPolicy retryPolicy = new RetryNTimes(1000, 3);
        ZookeeperProfile profile = new ZookeeperProfile(connect,rootNode);

        CuratorFramework client = CuratorFrameworkFactory.newClient(profile.getConnectStr(), retryPolicy);
        client.start();
        return client;
    }

//    @Test
    public void testDel(){
        try {
            getCuratorFramework("").delete().forPath("/schedule/task/testJob");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddJob(){
        String path="/schedule/task_info";
        CuratorFramework client = getCuratorFramework("/schedule");
        try {
            if(!ZkUtils.hasExistsPath(client,path)) {
                client.create().creatingParentContainersIfNeeded().forPath(path);
            }
            TaskInfo info = new TaskInfo();
            info.setBeanName("taskJobBean");
            info.setCronExpression("0 * * * * ?");
            info.setConcurrency(false);
            info.setMaxLimit(30);
            info.setThreads(1);
            String json = JSONObject.toJSONString(info);
            client.create().forPath(ZKPaths.makePath(path,info.getBeanName()),json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdatestatus(){
        ZookeeperProfile profile = new ZookeeperProfile("localhost:2181","/schedule");
        ZkUtils.updateStatus(profile,"taskJobBean","RUN");
    }
//    @Test
    public void testSeq(){
        String path="/schedule/log/taskJobBean";
        CuratorFramework client = getCuratorFramework("/schedule");
        try{
            List<String> list = client.getChildren().forPath(path);
            for(String id:list) {
                System.out.println(id);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
