package com.syj.schedule.common.service;


import com.syj.schedule.common.domain.PageView;
import com.syj.schedule.common.domain.ResultHandler;

/**
 * Created by syj on 2016/10/11 0011.
 */
public interface ZookeeperService<T> {

    ResultHandler save(T data, String account);

    ResultHandler delete(String flagName, String account);

    ResultHandler update(T data, String flagName, String account);

    T find(String flagName);

    PageView<T> query(Integer page, Integer pageSize, String flagName);
}
