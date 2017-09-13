package com.syj.schedule.back.task.service.impl;

import com.syj.schedule.back.task.domain.TaskInfo;
import com.syj.schedule.back.task.enums.TaskEnums;
import com.syj.schedule.common.domain.PageView;
import com.syj.schedule.common.domain.ResultHandler;
import com.syj.schedule.common.service.ZookeeperService;
import com.syj.schedule.common.service.ZookeeperServiceAdapter;
import com.syj.schedule.common.zk.ZookeeperClient;
import com.syj.schedule.cron.CronExpression;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.curator.utils.ZKPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by syj on 2016/8/16 0016.
 */
@Service
public class TaskManagerServiceImpl extends ZookeeperServiceAdapter<TaskInfo> implements ZookeeperService<TaskInfo> {

    @Autowired
    private ZookeeperClient zookeeperClient;

    private final String path = ZKPaths.makePath(TaskEnums.Config.SCHEDULE_PATH.getDesc(), "");




    @Override
    public ResultHandler save(TaskInfo data, String account) {
        data.setFlagName(data.getBeanName());
        return saveData(data, zookeeperClient, path);
    }

    @Override
    public ResultHandler delete(String flagName, String account) {
        return deleteData(flagName, zookeeperClient, path);
    }

    @Override
    public ResultHandler update(TaskInfo data, String flagName, String account) {
        ResultHandler resultHandler;
        if (!flagName.equals(data.getBeanName())) {
            deleteData(flagName, zookeeperClient, path);
            data.setFlagName(data.getBeanName());
            resultHandler = saveData(data, zookeeperClient, path);
        } else {
            resultHandler = updateData(data, flagName, zookeeperClient, path);
        }

        return resultHandler;
    }


    @Override
    public TaskInfo find(String flagName) {
        return findData(flagName, zookeeperClient, path);
    }

    @Override
    public PageView<TaskInfo> query(Integer page, Integer pageSize, String flagName) {

        PageView<TaskInfo> pageView = queryData(page, pageSize, flagName, zookeeperClient, path);

        for (TaskInfo taskInfo : pageView.getResult()) {
            CronExpression cronExpression = null;
            Date now = new Date();
            try {
                cronExpression = new CronExpression(taskInfo.getCronExpression());
                taskInfo.setNextExecutorTime(DateFormatUtils.format(cronExpression.getNextValidTimeAfter(now), "yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return pageView;
    }

}
