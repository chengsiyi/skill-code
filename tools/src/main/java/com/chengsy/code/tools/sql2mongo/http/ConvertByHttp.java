package com.chengsy.code.tools.sql2mongo.http;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengsiyi
 * @date 2022/5/13 11:36
 */
public class ConvertByHttp {

    private static final String URL = "https://www.sql2mongo.com/sql2mongo";
    public static void main(String[] args) {
        String sql = "select * from applist";
        Map<String, Object> param = new HashMap<>();
        param.put("sql",sql);
        String result = HttpUtil.post(URL, param);
        JSONObject convertResult = JSON.parseObject(result);
        System.out.println(convertResult.getString("mongo3"));
        System.out.println(JSON.toJSONString(convertResult, true));
    }

}
