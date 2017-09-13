package com.yili.schedule.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yili.schedule.config.TaskInfo;
import com.yili.schedule.config.ZookeeperProfile;
import com.yili.schedule.cron.CronExpression;
import com.yili.schedule.zk.ZkUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编辑任务信息
 * Created by lancey on 16/3/18.
 */
@Controller
@RequestMapping("/task/")
public class TaskInfoController {

    @Autowired
    private ZookeeperProfile zookeeperProfile;

    @RequestMapping("add.do")
    public String add(ModelMap map){
        map.put("taskInfo",new TaskInfo());
        return "edit_task";
    }

    @RequestMapping("edit.do")
    public String edit(ModelMap map,String beanName){
        TaskInfo taskInfo = ZkUtils.getTaskInfo(zookeeperProfile, beanName);
        if(taskInfo==null){
            throw new IllegalArgumentException("job不存在");
        }

        map.put("taskInfo",taskInfo);
        return "edit_task";
    }

    @RequestMapping("updateStatus.do")
    public String update(ModelMap map,String beanName,String status){

        ZkUtils.updateStatus(zookeeperProfile, beanName, status);
        return "redirect:/index.do";
    }



    @RequestMapping("save.do")
    public String save(TaskInfo taskInfo){
        try {
            CronExpression expression = new CronExpression(taskInfo.getCronExpression());
        } catch (ParseException e) {
            e.printStackTrace();
            return "edit_task";
        }
        ZkUtils.saveTaskInfo(zookeeperProfile,taskInfo);
        return "redirect:/index.do";
    }

    @RequestMapping("goImport.do")
    public String goImport(){
        return "import_task";
    }

    @RequestMapping("import.do")
    public String submitImport(ModelMap map,String content){
        int count = 0;
        if(StringUtils.isNotEmpty(content)) {
            Pattern pattern = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);
            Matcher m = pattern.matcher(content);
            List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
            while(m.find()){
                String s = m.group();
                JSONObject t = (JSONObject)JSON.parse(s);
                TaskInfo taskInfo = JSONObject.toJavaObject(t,TaskInfo.class);
                if(taskInfo!=null){
                    taskInfos.add(taskInfo);
                }
            }

            for(TaskInfo t:taskInfos){
                if(!ZkUtils.hasExistsPath(zookeeperProfile.createClient(),
                        zookeeperProfile.makePath(ZkUtils.getTaskInfoPath(zookeeperProfile),t.getBeanName()))) {
                    ZkUtils.saveTaskInfo(zookeeperProfile, t);
                    count++;
                }
            }
        }
        map.put("successCount",count);
        return "import_task";
    }
}
