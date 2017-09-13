package com.syj.schedule.common.enums;

/**
 * Created by syj on 2016/8/16 0016.
 */
public enum ResultType {
    SUCCESS("success"),
    ERROR("error");

    private String respType;

    private ResultType(String respType) {
        this.respType = respType;
    }

    public String getRespType() {
        return this.respType;
    }
}
