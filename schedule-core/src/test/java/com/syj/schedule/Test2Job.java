package com.syj.schedule;

import com.syj.schedule.config.TaskJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component("test2JobBean")
public class Test2Job implements TaskJob {
    /**
     * 读取数据列表
     *
     * @param config 配置控制的加载脚本，可以多机器执行一个任务时由该配置来决定数据的加载
     * @param size   返回的行数
     * @return
     */
    @Override
    public List selectList(String config, int size) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        return list;
    }

    /**
     * 执行一个任务，
     *
     * @param object
     * @return 成功时返回true, 失败返回false
     * @throws Exception 异常时按false处理
     */
    @Override
    public boolean execute(Object object) throws Exception {
        Integer id = (Integer)object;
        System.out.println(Thread.currentThread()+"  test2Job id:::::"+id);
        return true;
    }
}
