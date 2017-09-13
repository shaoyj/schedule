package com.yili.schedule;

import com.yili.schedule.config.ZookeeperProfile;
import com.yili.schedule.core.ScheduleSpringFactroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lancey on 16/3/5.
 */
@Configuration
@ComponentScan("")
public class SpringBeans {

    private String zookeeperlist="localhost:2181";

    @Autowired
    ZookeeperProfile profile;

    @Bean
    public ZookeeperProfile createProfile(){
        ZookeeperProfile profile = new ZookeeperProfile(zookeeperlist,"/schedule");
        return profile;
    }

    @Bean
    public ScheduleSpringFactroy createFactory(){
        ScheduleSpringFactroy factroy = new ScheduleSpringFactroy();
        factroy.setZookeeperProfile(profile);
        return factroy;
    }
}
