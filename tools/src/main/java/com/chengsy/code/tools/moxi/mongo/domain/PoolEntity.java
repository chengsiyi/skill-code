package com.chengsy.code.tools.moxi.mongo.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author chengsiyi
 * @date 2022/10/2 22:54
 */
@Data
@Document(collection = "pool")
public class PoolEntity {

    private String id;

    private String sTitle;
}
