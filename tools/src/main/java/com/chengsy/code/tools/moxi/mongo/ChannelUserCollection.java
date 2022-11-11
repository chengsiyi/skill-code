package com.chengsy.code.tools.moxi.mongo;

import com.chengsy.code.tools.moxi.mongo.domain.ChannelUserEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengsiyi
 * @date 2022/10/2 15:51
 */
@Repository
public class ChannelUserCollection {

    @Resource(name = "channelTemplate")
    private MongoTemplate mongoTemplate;

    public List<ChannelUserEntity> getUserDetail(Long appId, List<String> uid) {
        Query query = new Query(new Criteria("appId").is(appId));
        if (CollectionUtils.isNotEmpty(uid)) {
            query.addCriteria(new Criteria("uid").in(uid));
        } else {
            query.limit(100);
        }

        return mongoTemplate.find(query, ChannelUserEntity.class);
    }

    public List<ChannelUserEntity> getUserDetail(Long appId, Long pageNo, Integer pageSize) {
        Query query = new Query(new Criteria("appId").is(appId));
        query.skip(pageNo * pageSize);
        query.limit(pageSize);
        return mongoTemplate.find(query, ChannelUserEntity.class);
    }

    public Long getUserCount(Long appId) {
        Query query = new Query(new Criteria("appId").is(appId));
        return mongoTemplate.count(query, ChannelUserEntity.class);
    }

}
