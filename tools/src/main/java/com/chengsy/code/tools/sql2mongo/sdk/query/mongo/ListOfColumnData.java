package com.chengsy.code.tools.sql2mongo.sdk.query.mongo;

import java.util.ArrayList;
import java.util.List;

public class ListOfColumnData {

    List<OneColAndData> colData = new ArrayList<>();

    public ListOfColumnData(List<String> col, List<String> val) {
        for (int i = 0; i < col.size(); i++) {
            this.colData.add(new OneColAndData(col.get(i), val.get(i)));
        }
    }

    @Override
    public String toString() {
        return colData.toString().replace("[", "").replace("]", "");
    }

}
