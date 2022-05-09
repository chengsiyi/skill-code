package com.chengsy.code.chat.message;

import lombok.Data;

/**
 * @author chengsiyi
 * @date 2022/4/8 11:29
 */
@Data
public abstract class AbstractResponseMessage extends Message {

    private static final long serialVersionUID = 215239059337447642L;

    private boolean success;

    private String reason;

    private String code = "0";

    public AbstractResponseMessage() {
    }

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }
}

