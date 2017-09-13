package com.syj.schedule.common.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by syj on 2016/9/5 0005.
 */
public class BaseDomainZK implements Serializable {

    public static final long serialVersionUID = 123456789L;

    private String flagName;

    private Long objectId;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().
            disableHtmlEscaping().
            create();

    @Override
    public String toString() {
        return PRETTY_PRINT_GSON.toJson(this);
    }
}
