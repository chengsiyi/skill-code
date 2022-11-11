package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.DeleteMongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.DeleteSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.util.CustomStringUtil;

public class DeleteQueryConverter implements QueryConverter<DeleteSqlQuery> {

    @Override
    public DeleteMongoQuery covertToMongoQuery(DeleteSqlQuery deleteSqlQuery) {

        DeleteMongoQuery deleteMongoQuery = new DeleteMongoQuery();
        deleteMongoQuery.setTableName(deleteSqlQuery.getTableName());
        if(! deleteSqlQuery.getColumnNames().isEmpty()) {
            deleteMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(deleteSqlQuery.getColumnNames(), deleteSqlQuery.getColumnValues()));
        }
        return deleteMongoQuery;
    }


}
