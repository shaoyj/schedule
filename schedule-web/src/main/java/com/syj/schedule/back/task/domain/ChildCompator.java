package com.syj.schedule.back.task.domain;

import java.util.Comparator;

/**
 * Copyright (c) 2017/5/2 by ShaoYongJun
 * Company: Zoomy
 */
public class ChildCompator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }

}
