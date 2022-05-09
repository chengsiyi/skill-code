package com.chengsy.code.chat.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author chengsiyi
 * @date 2022/4/7 15:56
 */
@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {

    private static final long serialVersionUID = -6999685094215293733L;

    public LoginResponseMessage() {
    }

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return Message.LoginResponseMessage;
    }
}
