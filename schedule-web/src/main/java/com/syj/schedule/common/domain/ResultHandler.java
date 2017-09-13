package com.syj.schedule.common.domain;

import com.syj.schedule.common.exceptionm.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class ResultHandler implements Serializable {
    private static final long serialVersionUID = 3876137898792731045L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultHandler.class);
    protected int code;
    protected String message;

    public ResultHandler() {
        this.code = 0;
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void raise(Exception ex) {
        if (LOGGER.isDebugEnabled()) {
            ex.printStackTrace();
        }

        this.code = -1;
        this.message = ex.getMessage();
    }

    public void raise(SystemException ex) {
        if (LOGGER.isDebugEnabled()) {
            ex.printStackTrace();
        }

        this.code = ex.getCode();
        this.message = ex.getMessage();
    }

    public boolean hasSuccess() {
        return this.code == 0;
    }

    public String toString() {
        return "ResultHandler{code=" + this.code + ", message='" + this.message + '\'' + '}';
    }
}
