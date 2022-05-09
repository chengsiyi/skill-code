package com.chengsy.code.chat.message;

import lombok.Data;

/**
 * @author chengsiyi
 * @date 2022/4/7 15:56
 */
@Data
public class LoginRequestMessage extends Message {

    private static final long serialVersionUID = 2724604440088890422L;

    private String username;

    private String password;

    private String nickname;

    public LoginRequestMessage() {
    }

    public LoginRequestMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int getMessageType() {
        return Message.LOGIN_REQUEST_MESSAGE;
    }
}
