package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.IndexMongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.MongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.OneColAndData;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.IndexSqlQuery;

import java.util.ArrayList;
import java.util.List;

public class IndexQueryConverter implements QueryConverter<IndexSqlQuery>{

    @Override
    public MongoQuery covertToMongoQuery(IndexSqlQuery indexSqlQuery) {
        IndexMongoQuery indexMongoQuery = new IndexMongoQuery();
        indexMongoQuery.setDatabseName(indexSqlQuery.getTableName());
        indexMongoQuery.setIndexColumns(setOneColAndData(indexSqlQuery.getIndexOnColumns()));
        return indexMongoQuery;
    }

    private List<OneColAndData> setOneColAndData(List<String> columnValues) {
        List<OneColAndData> oneColAndDatas = new ArrayList<>();

        for(int i=0;i<columnValues.size();i++) {
            oneColAndDatas.add(new OneColAndData(columnValues.get(i), "1"));
        }
        return oneColAndDatas;
    }
}
