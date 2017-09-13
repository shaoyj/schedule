package com.syj.schedule.back.task.domain;


import com.syj.schedule.common.domain.BaseDomainZK;

/**
 * Created by syj on 2016/8/16 0016.
 */
public class TaskInfo extends BaseDomainZK {

    private String beanName;
    private String cronExpression = "0 * * * * ?";
    private String nextExecutorTime;
    private Integer maxLimit;
    private String config;
    private String memo;
    private Integer threads;
    private boolean concurrency;
    private String allowHosts;
    private String denyHosts;
    private String status;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getNextExecutorTime() {
        return nextExecutorTime;
    }

    public void setNextExecutorTime(String nextExecutorTime) {
        this.nextExecutorTime = nextExecutorTime;
    }

    public Integer getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Integer maxLimit) {
        this.maxLimit = maxLimit;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public boolean isConcurrency() {
        return concurrency;
    }

    public void setConcurrency(boolean concurrency) {
        this.concurrency = concurrency;
    }

    public String getAllowHosts() {
        return allowHosts;
    }

    public void setAllowHosts(String allowHosts) {
        this.allowHosts = allowHosts;
    }

    public String getDenyHosts() {
        return denyHosts;
    }

    public void setDenyHosts(String denyHosts) {
        this.denyHosts = denyHosts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
