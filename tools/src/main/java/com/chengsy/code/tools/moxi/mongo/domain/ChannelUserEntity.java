package com.chengsy.code.tools.moxi.mongo.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chengsiyi
 * @date 2022/10/2 15:57
 */
@Document(collection = "channel_user")
public class ChannelUserEntity {

    private String uid;

    private String detail;

    private JSONObject userExt;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public JSONObject getUserExt() {
        return userExt;
    }

    public void setUserExt(JSONObject userExt) {
        this.userExt = userExt;
    }
}
