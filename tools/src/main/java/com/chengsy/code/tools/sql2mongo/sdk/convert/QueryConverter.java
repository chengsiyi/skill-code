package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.MongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.SqlQuery;

public interface QueryConverter<T extends SqlQuery> {

    MongoQuery covertToMongoQuery(T t);
}
