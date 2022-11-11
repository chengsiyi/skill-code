package com.chengsy.code.tools;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP 请求工具类
 *
 * @author : liii
 * @version : 1.0.0
 * @date : 2015/7/21
 */
public class HttpUtil {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;
    private static final String CHARACTER_ENCODE = "UTF-8";


    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        connMgr.setValidateAfterInactivity(10000);

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        requestConfig = configBuilder.build();
    }


    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {
        logger.info("发送post请求，请求url={},请求参数为{}.", apiUrl, JSONObject.toJSONString(params));
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("model", "customer");
        httpPost.setHeader("store_code", "310117");
        httpPost.setHeader("method", "get");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setConfig(requestConfig);
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARACTER_ENCODE)));
        return sendPost(httpPost);
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param apiUrl API接口URL
     * @param params 参数map
     * @return
     */
    public static String doPostSSLWithHead(HttpPost httpPost, Map<String, Object> params) {
        logger.info("发送post请求，请求url={},请求参数为{}.", httpPost.getURI(), JSONObject.toJSONString(params));
        httpPost.setConfig(requestConfig);
        List<NameValuePair> pairList = new ArrayList<>(params.size());
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
            pairList.add(pair);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARACTER_ENCODE)));
        return sendPost(httpPost);
    }


    public static String doPostSSL(String url, String json) {
        logger.info("发送post请求，请求url={},请求参数为{}.", url, json);
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, CHARACTER_ENCODE);
        stringEntity.setContentEncoding(CHARACTER_ENCODE);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        return sendPost(httpPost);
    }

    public static String doPostSSLWithHead(HttpPost httpPost, String json) {
        logger.info("发送post请求，请求url={},请求参数为{}.", httpPost.getURI(), json);
        StringEntity stringEntity = new StringEntity(json, CHARACTER_ENCODE);
        stringEntity.setContentEncoding(CHARACTER_ENCODE);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        return sendPost(httpPost);
    }

    /**
     * 发送post请求
     *
     * @param apiUrl
     * @param httpEntity
     * @return
     */
    public static String doPostSSL(String apiUrl, HttpEntity httpEntity) {
        logger.info("发送post请求，请求url={},请求参数为{}.", apiUrl);
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("content-Type", "multipart/form-data");
        httpPost.setEntity(httpEntity);
        return sendPost(httpPost);
    }


    public static String sendPost(HttpPost post) {
        String resp = "";
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        try (CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, CHARACTER_ENCODE);
        } catch (IOException e) {
            logger.warn("发送post请求失败", e);
        }
        logger.info("post请求结果为:{}.", resp);
        return resp;
    }

    public static String sendGet(HttpGet httpGet){
        logger.info("发送get请求，请求url={}.", httpGet.getURI());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        httpGet.setConfig(requestConfig);
        String httpStr = "";
        try (CloseableHttpResponse resp = httpClient.execute(httpGet)) {
            //do something with resp
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = resp.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, CHARACTER_ENCODE);
        } catch (IOException e) {
            logger.warn("发送get请求失败", e);
        }
        logger.info("get请求结果为:{}.", httpStr);
        return httpStr;
    }



    public static String sendGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        return sendGet(httpGet);
    }

    public static String sendGet(String firstUrl, String redirectUrl) {
        HttpClientContext context = HttpClientContext.create();
        sendGet(firstUrl, context);
        return sendGet(redirectUrl, context);
    }

    private static String sendGet(String url, HttpClientContext context) {
        logger.info("发送get请求，请求url={}.", url);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        String httpStr = "";
        try (CloseableHttpResponse resp = httpClient.execute(httpGet, context)) {
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = resp.getEntity();
            if (entity == null) {
                return null;
            }

            httpStr = EntityUtils.toString(entity, CHARACTER_ENCODE);
        } catch (IOException e) {
            logger.warn("发送get请求失败", e);
        }
        logger.info("get请求结果为:{}.", httpStr);
        return httpStr;
    }


    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, (arg0, arg1) -> true);
        } catch (GeneralSecurityException e) {
            logger.error("创建ssl连接发生异常", e);
        }
        return sslsf;
    }

}
