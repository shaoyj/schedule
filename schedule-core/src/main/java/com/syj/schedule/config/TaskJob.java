package com.syj.schedule.config;

import java.util.List;

/**
 * 执行的job接口
 * Created by lancey on 16/2/28.
 */
public interface TaskJob<T> {

    /**
     * 读取数据列表
     * @param config 配置控制的加载脚本，可以多机器执行一个任务时由该配置来决定数据的加载
     * @param size 返回的行数
     * @return
     */
    public List<T> selectList(String config,int size);


    /**
     * 执行一个任务，
     * @param object
     * @return 成功时返回true,失败返回false
     * @throws Exception 异常时按false处理
     */
    public boolean execute(T object) throws Exception;
}
