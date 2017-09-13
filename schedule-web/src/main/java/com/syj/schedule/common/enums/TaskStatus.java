package com.syj.schedule.common.enums;

/**
 * Created by syj on 2016/8/16 0016.
 */
public enum TaskStatus {
    RUN("运行"),
    STOP("停止");

    private TaskStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public static String getZhDesc(String param) {
        for (TaskStatus deliveryStatus : values()) {
            if (deliveryStatus.name().equals(param)) {
                return deliveryStatus.getDesc();
            }
        }
        return param;
    }
}
