package com.chengsy.code.tools.sql2mongo.sdk.convert;

import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.InsertMongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.ListOfColumnData;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.MongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.InsertSqlQuery;

import java.util.ArrayList;
import java.util.List;

public class InsertQueryConverter implements QueryConverter<InsertSqlQuery> {

    @Override
    public MongoQuery covertToMongoQuery(InsertSqlQuery insertSqlQuery) {
        InsertMongoQuery insertMongoQuery = new InsertMongoQuery();

        insertMongoQuery.setTableName(insertSqlQuery.getTableName());
        insertMongoQuery.setListOfColumnDatas(getInsertDataMap(insertSqlQuery.getColumnNames(), insertSqlQuery.getColumnValues()));

        return insertMongoQuery;
    }

    private List<ListOfColumnData> getInsertDataMap(List<String> columnValues, List<List<String>> list) {
        List<ListOfColumnData> listOfColumnDatas = new ArrayList<>();

        for (List<String> columnDataValues : list) {
            if (columnValues.size() == columnDataValues.size()) {
                listOfColumnDatas.add(new ListOfColumnData(columnValues, columnDataValues));
            } else {
                throw new RuntimeException("Data Column & Values are mismatched.");
            }
        }
        return listOfColumnDatas;
    }
}
