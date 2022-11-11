package com.chengsy.code.tools.moxi.mongo;

import com.chengsy.code.tools.moxi.mongo.domain.PoolEntity;
import com.chengsy.code.tools.moxi.mongo.domain.UserCodeEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengsiyi
 * @date 2022/10/2 20:59
 */
@Repository
public class UserCodeCollection {
    @Resource(name = "platformTemplate")
    private MongoTemplate mongoTemplate;

    public List<UserCodeEntity> getUserCode(String activityId, Double startTime, Double endTime) {

        Query query = new Query(new Criteria("sActiveId").is(activityId).and("eStatus").ne(1));

        if (startTime != null && endTime != null) {
            query.addCriteria(new Criteria("iCreateTime").gt(startTime).lt(endTime));
        } else {
            query.limit(10);
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "iCreateTime");

        query.with(sort);
        return mongoTemplate.find(query, UserCodeEntity.class);
    }

    public List<PoolEntity> getPoolList(String activityId, List<String> poolIds) {
        Query query = new Query(new Criteria("sActiveId").is(activityId));
        if (CollectionUtils.isNotEmpty(poolIds)) {
            query.addCriteria(new Criteria("_id").in(poolIds));
        }
        return mongoTemplate.find(query, PoolEntity.class);

    }
}
