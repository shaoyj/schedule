
package com.syj.schedule.back.index;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseBackController {

    /**
     * initBinder Create on 2016-07-18
     *
     * @param binder
     * @description 把页面传递进来的字符串时间转换成date类型
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }


    public  String getLoginUserName() {
        return "admin";
    }

}

