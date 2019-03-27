package com.gduf.clock.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class Request {
    public static CloseableHttpResponse get(String url) {
        return get(url, null);
    }

    public static CloseableHttpResponse get(String url, HashMap<String, String> headers) {
        HttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                Set<Map.Entry<String, String>> entrySet = headers.entrySet();
                Iterator<Map.Entry<String, String>> it = entrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = it.next();
                    httpGet.setHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }

            response = (CloseableHttpResponse) client.execute(httpGet);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static CloseableHttpResponse post(String url, HashMap<String, String> headers, HashMap<String, String> params) {
        HttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //装填参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey().toString(), entry.getValue().toString()));
            }
        }

        try {
            HttpPost httpPost = new HttpPost(url);
            if (headers != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
                Set<Map.Entry<String, String>> entrySet = headers.entrySet();
                Iterator<Map.Entry<String, String>> it = entrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = it.next();
                    httpPost.setHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }
            response = (CloseableHttpResponse) client.execute(httpPost);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String getContent(CloseableHttpResponse response) {
        //4.获取响应的实体内容，就是我们所要抓取得网页内容
        HttpEntity entity = response.getEntity();

        //5.将其打印到控制台上面
        //方法一：使用EntityUtils
        String content = "";
        if (entity != null) {
            try {
                content += EntityUtils.toString(entity, "utf-8");
                //释放实体
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
