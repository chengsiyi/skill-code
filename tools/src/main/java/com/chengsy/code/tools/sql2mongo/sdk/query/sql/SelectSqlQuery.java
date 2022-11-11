package com.chengsy.code.tools.sql2mongo.sdk.query.sql;

import com.chengsy.code.tools.sql2mongo.sdk.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectSqlQuery extends SqlQuery {

    private final List<String> selectClause = new ArrayList<>();
    private final List<String> whereClauseColumnNames = new ArrayList<>();
    private final List<String> whereClauseColumnValues = new ArrayList<>();


    public SelectSqlQuery(String sqlQ) {
        String tempSql = sqlQ.toLowerCase();
        int selectIndex = tempSql.indexOf(Constant.SELECT) + 6;
        int fromIndex = tempSql.indexOf(Constant.FROM);
        int whereIndexOrLastIndex = tempSql.contains(Constant.WHERE) ? tempSql.indexOf(Constant.WHERE): sqlQ.length();
        this.setTableName(sqlQ.substring(fromIndex + 4, whereIndexOrLastIndex).trim());
        setListForSelect(sqlQ.substring(selectIndex, fromIndex).trim());
        if (tempSql.contains(Constant.WHERE)) {
            setWhereClauseColumnAndValues(sqlQ.substring(whereIndexOrLastIndex));
        }
    }

    public List<String> getSelectClause() {
        return selectClause;
    }

    public List<String> getWhereClauseColumnNames() {
        return whereClauseColumnNames;
    }

    public List<String> getWhereClauseColumnValues() {
        return whereClauseColumnValues;
    }

    private void setListForSelect(String columnsOrAll) {
        if (columnsOrAll.equals(Constant.STAR)) {
            return;
        } else {
            this.selectClause.addAll(Arrays.stream(columnsOrAll.split(",")).map(String::trim).collect(Collectors.toList()));
        }
    }

    public void setWhereClauseColumnAndValues(String deleteQuery) {
        String[] whereClauseData = deleteQuery.replace(Constant.WHERE, "")
                .trim().split(Constant.AND);
        for (String pairOfkeyData : whereClauseData) {
            List<String> keyData = Arrays.stream(pairOfkeyData.split("=")).map(String::trim).collect(Collectors.toList());
            if (keyData.size() == 2) {
                whereClauseColumnNames.add(keyData.get(0));
                whereClauseColumnValues.add(keyData.get(1));
            }
        }
    }

    @Override
    public String toString() {
        return " SELECT " + selectClause + "  from " + this.getTableName();
    }
}
