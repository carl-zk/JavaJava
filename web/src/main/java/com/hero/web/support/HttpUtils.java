package com.hero.web.support;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;

public class HttpUtils {
    private static CloseableHttpClient httpClient;

    static {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5 * 1000)
                .setSocketTimeout(5 * 1000)
                .setCookieSpec(CookieSpecs.STANDARD)
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(cm)
                .disableCookieManagement()
                .build();
    }

    public static JSONObject get(String url, Header... headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeaders(headers);
        httpGet.addHeader("Content-Type", "application/json");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        return extractJsonFromResponse(response);
    }

    public static JSONObject post(String url, JSONObject body, Header... headers) throws IOException {
        Assert.notNull(body, "body can't be null.");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(headers);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(body.toJSONString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return extractJsonFromResponse(response);
    }

    private static JSONObject extractJsonFromResponse(CloseableHttpResponse response) throws IOException {
        try {
            HttpEntity httpEntity = response.getEntity();
            JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
            EntityUtils.consume(httpEntity);
            return result;
        } finally {
            response.close();
        }
    }
}
