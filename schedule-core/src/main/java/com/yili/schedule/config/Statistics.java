package com.yili.schedule.config;

import java.util.Date;

/**
 * 统计信息
 * Created by lancey on 15/11/19.
 */
public class Statistics {

    private String host;
    /**
     * job加载数据的次数
     */
    private long fetchCount;
    /**
     * 加载的数据量
     */
    private long fetchDataCount;
    /**
     * 执行成功的数量
     */
    private long successCount;
    /**
     * 开启时间
     */
    private Date startTime;
    /**
     * 下次执行时间
     */
    private Date nextTime;
    /**
     * 心跳时间
     */
    private Date heatBeatTime;


    public long getFetchCount() {
        return fetchCount;
    }

    public void setFetchCount(long fetchCount) {
        this.fetchCount = fetchCount;
    }

    public long getFetchDataCount() {
        return fetchDataCount;
    }

    public void setFetchDataCount(long fetchDataCount) {
        this.fetchDataCount = fetchDataCount;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(long successCount) {
        this.successCount = successCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Date getHeatBeatTime() {
        return heatBeatTime;
    }

    public void setHeatBeatTime(Date heatBeatTime) {
        this.heatBeatTime = heatBeatTime;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
