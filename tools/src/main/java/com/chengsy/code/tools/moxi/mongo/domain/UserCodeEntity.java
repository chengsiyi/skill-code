package com.chengsy.code.tools.moxi.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chengsiyi
 * @date 2022/10/2 21:42
 */
@Data
@Document(value = "usercode")
public class UserCodeEntity {

    private String sUserId;
    private String sPoolId;

    private Double iCreateTime;


}
