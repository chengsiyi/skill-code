package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.UpdateMongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.UpdateSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.util.CustomStringUtil;

public class UpdateQueryConverter implements QueryConverter<UpdateSqlQuery> {

    @Override
    public UpdateMongoQuery covertToMongoQuery(UpdateSqlQuery updateSqlQuery) {

        UpdateMongoQuery updateMongoQuery = new UpdateMongoQuery();
        updateMongoQuery.setDatabase(updateSqlQuery.getTableName());
        if( !updateSqlQuery.getWhereClauseColumnNames().isEmpty()) {
            updateMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(
                    updateSqlQuery.getWhereClauseColumnNames(), updateSqlQuery.getWhereClauseColumnValues()));
        }
        updateMongoQuery.setSetClause(CustomStringUtil.getListOfPairValues(
                updateSqlQuery.getSetClauseColumnNames(), updateSqlQuery.getSetClauseColumnValues()));

        return updateMongoQuery;
    }
}
