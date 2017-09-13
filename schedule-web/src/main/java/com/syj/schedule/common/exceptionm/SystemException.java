package com.syj.schedule.common.exceptionm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemException.class);
    private static final long serialVersionUID = 406696880674537950L;
    private int code;

    public SystemException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public SystemException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public synchronized Throwable fillInStackTrace() {
        return LOGGER.isDebugEnabled() ? super.fillInStackTrace() : null;
    }
}

