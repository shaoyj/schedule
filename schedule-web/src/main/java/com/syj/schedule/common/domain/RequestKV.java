package com.syj.schedule.common.domain;

import java.io.Serializable;

public class RequestKV  implements Serializable {
    private static final long serialVersionUID = -1359671272627378348L;
    private String name;
    private String value;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestKV(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

