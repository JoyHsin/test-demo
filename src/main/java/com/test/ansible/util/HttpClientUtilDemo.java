package com.test.ansible.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class HttpClientUtilDemo {
//    private static CloseableHttpClient httpClient;
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().
        setRedirectStrategy(new LaxRedirectStrategy()).build();
//    /**
//     * 信任SSL证书
//     */
//    static {
//        try {
//            SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
//            RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
//            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext).setSSLHostnameVerifier((x, y) -> true).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient is error status code :"
                        + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 发送json格式的报文 StringEntity
     * @param url
     * @param jsonString
     * @return
     */
    public static String doPost(String url, String jsonString) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            String username = "admin";
            String password = "tower@123";
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(jsonString, "utf-8");
            //添加http头信息
            httpPost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
            httpPost.addHeader("Content-Type", "application/json");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient is error status code :"
                        + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String httpClientWithBasicAuth() {
//        //增加下面两行代码
//        Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
//        Protocol.registerProtocol("https", myhttps);

        String username = "admin";
        String password = "tower@123";
//        String uri = "https://10.119.85.113/api/v2/job_templates/7/launch/";
        String uri = "https://10.119.85.113/api/v2/hosts";

        Map<String, String> paramMap = new HashMap<String,String>();
//        paramMap.put("xxx", "xxxx");

        String result = "";
        try {
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            //绕过证书
            CloseableHttpClient closeableHttpClient = httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();

            HttpPost httpPost = new HttpPost(uri);

            //String a = Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes());
            //添加http头信息
            httpPost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
            httpPost.addHeader("Content-Type", "application/json");
            //httpPost.addHeader("Authorization","Basic "+a);

            StringEntity s = new StringEntity(JSON.toJSONString(paramMap));
            s.setContentEncoding("UTF-8");
            httpPost.setEntity(s);


            HttpResponse httpResponse = null;
            HttpEntity entity = null;
            try {
                httpResponse = closeableHttpClient.execute(httpPost);
                entity = httpResponse.getEntity();
                System.out.println("httpResponse==="+httpResponse.getStatusLine());
                if( entity != null ){
                    result = EntityUtils.toString(entity);
                    System.out.println("result===="+result);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 关闭连接
            closeableHttpClient.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre1.8.0_181\\lib\\security\\jssecacerts");

        String url = "https://10.119.85.113/api/v2/hosts";
        String param = "";
        String result = doPost(url, param);
        System.out.println(result);

    }
}
