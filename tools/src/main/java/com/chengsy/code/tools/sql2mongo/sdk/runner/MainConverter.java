package com.chengsy.code.tools.sql2mongo.sdk.runner;

import com.chengsy.code.tools.sql2mongo.sdk.convert.DeleteQueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.convert.IndexQueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.convert.InsertQueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.convert.QueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.convert.SelectQueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.convert.UpdateQueryConverter;
import com.chengsy.code.tools.sql2mongo.sdk.query.mongo.MongoQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.DeleteSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.IndexSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.InsertSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.SelectSqlQuery;
import com.chengsy.code.tools.sql2mongo.sdk.query.sql.UpdateSqlQuery;

public class MainConverter {

    public static MongoQuery getMongoQuery(String sql) {
        QueryConverter queryConverter;
        MongoQuery mongoQuery = null;
        sql = sql.replace(";", "");
        String optType = sql.substring(0, sql.indexOf(' ')).toLowerCase();
        if (optType.startsWith("select")) {
            queryConverter = new SelectQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new SelectSqlQuery(sql));
        } else if (optType.startsWith("insert")) {
            queryConverter = new InsertQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new InsertSqlQuery(sql));
        } else if (optType.startsWith("delete")) {
            queryConverter = new DeleteQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new DeleteSqlQuery(sql));
        } else if (optType.startsWith("update")) {
            queryConverter = new UpdateQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new UpdateSqlQuery(sql));
        } else if (optType.contains("index") && optType.contains("create")) {
            queryConverter = new IndexQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new IndexSqlQuery(sql));
        }
        return mongoQuery;
    }

    public static void main(String[] args) throws Exception {
        String sql = "select * from shopperlist";

        System.out.println(getMongoQuery(sql));
    }
}
