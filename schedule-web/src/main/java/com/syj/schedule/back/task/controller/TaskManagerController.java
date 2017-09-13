package com.syj.schedule.back.task.controller;

/**
 * Created by syj on 2016/8/15 0015.
 */


import com.syj.schedule.back.index.BaseBackController;
import com.syj.schedule.back.task.domain.TaskInfo;
import com.syj.schedule.back.task.service.impl.TaskJobLogService;
import com.syj.schedule.common.domain.PageView;
import com.syj.schedule.common.domain.ResultHandler;
import com.syj.schedule.common.enums.TaskStatus;
import com.syj.schedule.common.interceptor.HttpLocalThread;
import com.syj.schedule.common.service.ZookeeperService;
import com.syj.schedule.common.util.WebUtils;
import com.syj.schedule.config.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TaskManagerController extends BaseBackController {

    @Autowired
    private ZookeeperService<TaskInfo> dataBaseService;

    @RequestMapping("/taskManager/index.html")
    public String index(ModelMap modelMap, Integer page) {
        PageView<TaskInfo> pageView = dataBaseService.query(page, 10, null);
        pageView.makeRequestUrl(HttpLocalThread.getRequest(), "/taskManager/search");
        modelMap.addAttribute("pageView", pageView);
        return "config/taskManager/index";
    }


    @RequestMapping("/taskManager/search")
    public String search(ModelMap modelMap, Integer page, Integer pageSize, String beanName) {
        PageView<TaskInfo> pageView = dataBaseService.query(page, pageSize, beanName);

        pageView.makeRequestUrl(HttpLocalThread.getRequest());
        modelMap.addAttribute("pageView", pageView);
        return "config/taskManager/search";
    }

    @RequestMapping("/taskManager/toAdd.html")
    public String ToAdd() {
        return "config/taskManager/add";
    }

    @RequestMapping(value = "/taskManager/add")
    @ResponseBody
    public Object add(TaskInfo data) {
        return dataBaseService.save(data, getLoginUserName());
    }

    @RequestMapping("/taskManager/toEdit.html")
    public String toEdit(String beanName, ModelMap modelMap, Integer page) {
        TaskInfo data = dataBaseService.find(beanName);
        modelMap.put("taskInfo", data);
        modelMap.put("page", page);
        return "config/taskManager/edit";
    }

    @RequestMapping(value = "/taskManager/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(TaskInfo data, String flagName) {

        return dataBaseService.update(data, flagName, getLoginUserName());
    }

    @RequestMapping(value = "/taskManager/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String beanName) {
        Assert.notNull(beanName, "beanName is null");
        return dataBaseService.delete(beanName, getLoginUserName());
    }

    @RequestMapping(value = "/taskManager/switchStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object switchStatus(String beanName) {
        Assert.notNull(beanName, "beanName is null");

        TaskInfo taskInfo = dataBaseService.find(beanName);
        if (TaskStatus.RUN.name().equals(taskInfo.getStatus())) {
            taskInfo.setStatus(TaskStatus.STOP.name());
        } else {
            taskInfo.setStatus(TaskStatus.RUN.name());
        }

        ResultHandler switchResult = dataBaseService.update(taskInfo, taskInfo.getBeanName(), getLoginUserName());
        return switchResult;
    }


    @Autowired
    private TaskJobLogService taskJobLogService;

    @RequestMapping("/taskManager/taskLog")
    public String showLog(ModelMap map, String flagName) {


        PageView<Statistics> pageView = new PageView<>();
        pageView.setPageSize(10);
        pageView.setCurrentPage(WebUtils.getPage(HttpLocalThread.getRequest()));

        pageView = taskJobLogService.queryPage(pageView, flagName);

        map.addAttribute("pageView", pageView);
        map.put("jobName", flagName);
        pageView.makeRequestUrl(HttpLocalThread.getRequest(), "/taskManager/taskLog");

        return "config/taskManager/task_log";
    }
}
