package com.chengsy.code.tools;

import org.apache.http.client.methods.HttpGet;

/**
 * @author chengsiyi
 * @date 2022/7/26 14:10
 */
public class HttpDemo {

    static final String url = "https://gdlx.iyiplus.com/gateway/api/jsonrpc.jsp";
    static final String token = "lnScoHm7S0WGR0pZXTpg5r/Zw6fSFeQ54WcEp1TF96QM8Odk/NqmSxwrZzxMhdWCEs" +
            "+oy5t4BCWiNPrMI6KIyyBPEe3t7aANKxeaCtkpS+wNl3DA7SGvsV4MowMozL30y4KI5TVgBr" +
            "/+urd6XE0szDqTjYT6OjJLNG7RqAwsPcHJqah3oxOHeIWZv/LMG2WM";
    //    static final String code = "0a5a9694-cc46-4e10-a34a-83de90d4771a-1658815673610";
    static final String code = "86404ae4-5e13-4912-8c58-0e4aea6d1cb4-1658815161148";

    public static void main(String[] args) {

//        JSONObject json1 = new JSONObject();
//        json1.put("jsonrpc", "2.0");
//        json1.put("method", "gxzy.getMemberAndLexiangIDByThirdPartyCode");
//        json1.put("params", code);
//
//        HttpPost post = new HttpPost(url);
//        post.setHeader("user-agent", "JSONRPC NodeJS Client");
//        post.setHeader("Content-Type", "application/json");
//
//        String s = HttpUtil.doPostSSLWithHead(post, json1.toJSONString());
//        if (JSON.parseObject(s).getJSONObject("error").getInteger("code").equals(592)){
//            JSONObject json2 = new JSONObject();
//            json2.put("jsonrpc", "2.0");
//            json2.put("method", "backend@keys.participant");
//            json2.put("params", token);
//            HttpPost post2 = new HttpPost(url);
//            post2.setHeader("user-agent", "JSONRPC NodeJS Client");
//            post2.setHeader("Content-Type", "application/json");
//            String r2 = HttpUtil.doPostSSLWithHead(post2, json2.toJSONString());
//        }


        String openId = "13687158672";
        String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFjNzZkNzJjYTVmYjFiNGI4ODNmNDYzM2Q5YWE5MDEyODcwZjkyOTZlMDFiYjlkMTFiNmJlMDUxNTQ3Zjk0YTU4YTRhZWEyYTc2YzIwYTcyIn0.eyJhdWQiOiIyNDgyMCIsImp0aSI6ImFjNzZkNzJjYTVmYjFiNGI4ODNmNDYzM2Q5YWE5MDEyODcwZjkyOTZlMDFiYjlkMTFiNmJlMDUxNTQ3Zjk0YTU4YTRhZWEyYTc2YzIwYTcyIiwiaWF0IjoxNjU5NDExMDQ2LCJuYmYiOjE2NTk0MTEwNDYsImV4cCI6MTY1OTQxODI0Niwic3ViIjoiIiwic2NvcGVzIjpbXX0.QmRSDwqgmYoUYvA9L-AGlQ0sPnMND24_FHWjcf4nZkWzYSbgFAyKh9fMSmIzYO_Nj7nL3naCbagpXGIzml4K44sc35-ZeXSbUnnq7PADy2j-oOrAS0z3xxBn6sl-br0HWSlaC7I-8Ndr_8FRY-P8oxXz8rQNDas7FjT_BCCt4U0_0fXc9-PlUqVo6Oy2QLvBariIJOnYbClUeW4jMXK0zhhzH2CkCElWssPz1ihSaoAiLMHnSz3EEffDqFAwK1GW4bTkE3YJZiW0mbjXMHZWNv7-62lO17hV99my8MMg1NTz60RjT_FY8T02djhPKq8A5-GNP_hspWF2zyqOLiGuZbi3EgDe_ubVE2oFNbvaKSaOkTSlxjjgJbFNPlHdh2y8cxqwBeyZvs7M_mCli8oNrCMl3-miOA0mq-5NtzCZGyWeiFmRLvbtgDc66nz94L5HFCsDOVSQIEHeb3Fb55TYMHHkDwF6vZzddojZbKKayH3vAe2lAqTBbcjAapcY0F8Y3hNTjIWYpBoOT0JiVqPvoVyCLLSwmLNWW_lTGjFMWcsNMUXW0QknZnkBlY5WmioIk4kf67A7LYB25M5ZDHNuVuti6i3rbznJoRTQeaLzDY7snDl-gAlvGYAdrz0tQwIG26TgOR1k_Mg4zOUlKMXPOkZiAf3xxuQ9g2JosmxShPY";
        String url = "https://lxapi.lexiangla.com/cgi-bin/v1/staffs/13687158672/classes?learn_status=2";
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization",accessToken);
        String s = HttpUtil.sendGet(get);
        System.out.println(s);
    }
}
