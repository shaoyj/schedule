package com.yili.schedule.config;

/**
 * Created by lancey on 15/11/19.
 */
public class TaskInfo {

    /**
     * 对应的spring bean
     */
    private String beanName;
    /**
     * 调度cron
     */
    private String cronExpression="0 * * * * ?";

    /**
     * 下次执行时间
     */
    private String nextExecutorTime;
    /**
     * 最大行数
     */
    private int maxLimit;

    /**
     * 配置内容
     */
    private String config;

    /**
     * 备注
     */
    private String memo;

    /**
     * 线程数
     */
    private int threads;

    /**
     * 是否允许并行
     */
    private boolean concurrency;

    /**
     * 允许运行的机器
     */
    private String allowHosts;

    /**
     * 拒绝参数的服务器列表
     */
    private String denyHosts;

    /**
     * job状态,默认是不运行
     */
    private TaskStatus status = TaskStatus.STOP;


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

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public boolean isConcurrency() {
        return concurrency;
    }

    public void setConcurrency(boolean concurrency) {
        this.concurrency = concurrency;
    }



    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getDenyHosts() {
        return denyHosts;
    }

    public void setDenyHosts(String denyHosts) {
        this.denyHosts = denyHosts;
    }

    public String getAllowHosts() {
        return allowHosts;
    }

    public void setAllowHosts(String allowHosts) {
        this.allowHosts = allowHosts;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNextExecutorTime() {
        return nextExecutorTime;
    }

    public void setNextExecutorTime(String nextExecutorTime) {
        this.nextExecutorTime = nextExecutorTime;
    }
}
