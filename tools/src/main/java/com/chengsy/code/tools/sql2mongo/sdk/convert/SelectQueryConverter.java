package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.FindMongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.OneColAndData;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.SelectSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.util.CustomStringUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectQueryConverter implements QueryConverter<SelectSqlQuery> {

    @Override
    public FindMongoQuery covertToMongoQuery(SelectSqlQuery selectSqlQuery) {

        FindMongoQuery findMongoQuery = new FindMongoQuery();
        findMongoQuery.setTableName(selectSqlQuery.getTableName());
        findMongoQuery.setSelectClause(setOneColAndData(selectSqlQuery.getSelectClause()));
        findMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(selectSqlQuery.getWhereClauseColumnNames(),
                selectSqlQuery.getWhereClauseColumnValues()));
        return findMongoQuery;
    }

    private List<OneColAndData> setOneColAndData(List<String> columnValues) {
        List<OneColAndData> oneColAndDatas = new ArrayList<>();

        for (String columnValue : columnValues) {
            oneColAndDatas.add(new OneColAndData(columnValue, "1"));
        }
        return oneColAndDatas;
    }

}
