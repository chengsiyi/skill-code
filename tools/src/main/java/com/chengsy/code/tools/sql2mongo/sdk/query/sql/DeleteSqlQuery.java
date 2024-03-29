package com.chengsy.code.tools.sql2mongo.sdk.query.sql;

import com.chengsy.code.tools.sql2mongo.sdk.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteSqlQuery extends SqlQuery {

    private List<String> columnNames = new ArrayList<>();
    private List<String> columnValues = new ArrayList<>();

    public DeleteSqlQuery(String deleteQuery) {
        String[] deleteQueryArray = deleteQuery.split(" ");
        this.setTableName(deleteQueryArray[2]);
        if (deleteQueryArray.length > 3) {
            setColumnAndValues(deleteQuery);
        }
    }

    public void setColumnAndValues(String deleteQuery) {
        String tempSql = deleteQuery.toLowerCase();

        String[] whereClauseData = tempSql.replace(Constant.DELETE, "")
                .replace(Constant.FROM, "")
                .replace(Constant.WHERE, "")
                .replace(this.getTableName(), "")
                .trim().split(",");
        for (String pairOfkeyData : whereClauseData) {
            List<String> keyData = Arrays.stream(pairOfkeyData.split("=")).map(String::trim).collect(Collectors.toList());
            if (keyData.size() == 2) {
                columnNames.add(keyData.get(0));
                columnValues.add(keyData.get(1));
            }
        }
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<String> getColumnValues() {
        return columnValues;
    }


}
