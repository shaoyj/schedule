package com.syj.schedule.back.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by syj on 2016/9/7 0007.
 */
@Controller
public class IndexController {


    /**
     * 测试
     *
     * @return
     */
    @RequestMapping("/ok.html")
    @ResponseBody
    String hello() {
        return "ok";
    }

    /**
     * 主入口
     *
     * @return
     */
    @RequestMapping("/index.html")
    public String home() {

        return "home/index";
    }

    @RequestMapping("/")
    public String index() {

        return "home/index";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String t1() {
        return "error";
    }

}
