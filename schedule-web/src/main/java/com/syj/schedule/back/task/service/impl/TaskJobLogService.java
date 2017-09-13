package com.syj.schedule.back.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syj.schedule.back.task.domain.ChildCompator;
import com.syj.schedule.back.task.enums.TaskEnums;
import com.syj.schedule.common.domain.PageView;
import com.syj.schedule.common.zk.ZookeeperClient;
import com.syj.schedule.config.Statistics;
import org.apache.curator.utils.ZKPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright (c) 2017/5/2 by ShaoYongJun
 * Company: Zoomy
 */
@Service
public class TaskJobLogService {

    @Autowired
    private ZookeeperClient zookeeperClient;


    /**
     * 获取定时任务执行日志
     *
     * @param pageView
     * @param beanName
     * @return
     */
    public PageView<Statistics> queryPage(PageView<Statistics> pageView, String beanName) {

        Assert.notNull(pageView, "pageView is null");

        String path = ZKPaths.makePath(TaskEnums.Config.ROOT_LOG.getDesc(), beanName);

        List<Statistics> result = new ArrayList<>();
        try {
            List<String> list = zookeeperClient.getClient().getChildren().forPath(path);

            pageView.setTotalSize(list.size());

            Collections.sort(list, new ChildCompator());

            int fromIndex = (pageView.getCurrentPage() - 1) * pageView.getPageSize();
            int toIndex = pageView.getCurrentPage() * pageView.getPageSize();
            if (toIndex > list.size()) {
                toIndex = list.size();
            }
            List<String> pageItem = list.subList(fromIndex, toIndex);
            for (String it : pageItem) {
                byte[] buf = zookeeperClient.getClient().getData().forPath(ZKPaths.makePath(path, it));
                Statistics tics = JSONObject.toJavaObject(JSON.parseObject(new String(buf)), Statistics.class);
                result.add(tics);
            }

            pageView.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return pageView;
    }

}
