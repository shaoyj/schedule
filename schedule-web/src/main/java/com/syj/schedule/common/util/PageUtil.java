package com.syj.schedule.common.util;

public class PageUtil {

    public PageUtil() {
    }

    public static Integer curPageDeal(Integer curPage) {
        return curPage != null && curPage.intValue() > 0 ? curPage : new Integer(1);
    }

    public static Integer pageSizeDeal(Integer pageSize) {
        return pageSize == null ? new Integer(10) : pageSize;
    }


}
