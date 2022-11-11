package com.chengsy.code.tools.sql2mongo.sdk.query.sql;

import com.chengsy.code.tools.sql2mongo.sdk.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndexSqlQuery extends SqlQuery {

    private final String tableName;
    private List<String> indexOnColumns = new ArrayList<>();

    public IndexSqlQuery(String indexSqlQuery) {
        String[] sqlArray = indexSqlQuery.split(" ");
        String tempSql = indexSqlQuery.toLowerCase();
        if(sqlArray.length > 4 ) {
            int onIndex= tempSql.indexOf(Constant.ON)+2;
            int startIndex= indexSqlQuery.indexOf(Constant.ROUND_OPENING_BRACKET);
            int endIndex= indexSqlQuery.indexOf(Constant.ROUND_CLOSING_BRACKET);
            this.tableName = indexSqlQuery.substring(onIndex,startIndex).trim();
            this.indexOnColumns = setIndexOnCoulmns(indexSqlQuery.substring(startIndex+1,endIndex));
        } else {
            throw new RuntimeException("Create INDEX query validation fails");
        }
    }

    private List<String> setIndexOnCoulmns(String columns) {
        return Arrays.stream(columns.split(",")).map(String::trim).collect(Collectors.toList());
    }

    @Override
    public String getTableName() {
        return tableName;
    }


    public List<String> getIndexOnColumns() {
        return indexOnColumns;
    }

    @Override
    public String toString() {
        return "IndexSqlQuery{" +
                ", tableName='" + tableName + '\'' +
                ", indexOnColumns=" + indexOnColumns +
                '}';
    }
}
