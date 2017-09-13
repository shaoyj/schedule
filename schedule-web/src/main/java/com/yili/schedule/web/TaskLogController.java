package com.yili.schedule.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yili.schedule.config.Statistics;
import com.yili.schedule.config.ZookeeperProfile;
import org.apache.curator.utils.ZKPaths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 查看task的执行日志，
 * 一般日志会保存最后的一部分，一般由服务当中的不参与运行job的机器来完成，由主机指定
 * Created by lancey on 16/3/20.
 */
@Controller
@RequestMapping("/log/")
public class TaskLogController {

    @Autowired
    private ZookeeperProfile zookeeperProfile;
    private int pageSize=30;

    @RequestMapping("taskLog.do")
    public String showLog(ModelMap map,String beanName,@RequestParam(defaultValue = "1")int page){
        String path = zookeeperProfile.makePath("log",beanName);
        List<Statistics> result = new ArrayList<Statistics>();
        try {
            List<String>  list = zookeeperProfile.createClient().getChildren().forPath(path);
            map.put("totalSize",list.size());

            Collections.sort(list,new ChildCompator());

            int fromIndex = (page-1)*30;
            int toIndex = page*30;
            if(toIndex>list.size()){
                toIndex=list.size();
            }
            List<String> pageItem = list.subList(fromIndex,toIndex);
            for(String it:pageItem){
                byte[] buf = zookeeperProfile.createClient().getData().forPath(ZKPaths.makePath(path,it));
                Statistics tics = JSONObject.toJavaObject(JSON.parseObject(new String(buf)),Statistics.class);
                result.add(tics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("result",result);
        map.put("jobName",beanName);
        return "task_log";
    }

}
