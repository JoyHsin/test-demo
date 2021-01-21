package com.test.ansible.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.util.*;


public class TestHttp {

    private static String url = "https://ansible/api/v2/hosts/";
//    private static String url = "https://ansible/api/v2/job_templates/7/launch/";
    private static String username = "admin";
    private static String password = "tower@123";
    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static void post() {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost("https://10.119.85.113/api/v2/hosts");
        //添加http头信息
        httppost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
        httppost.addHeader("Content-Type", "application/json");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("type", "house"));
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("--------------------------------------");
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
                    System.out.println("--------------------------------------");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String doPostWithBasicAuth(String url,Map<String, Object> paramMap) {
        String username = "admin";
        String password = "tower@123";
//        String realUrl = getReal(url);

        String result = "";
        try {
            // 创建HttpClientBuilder
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            //绕过证书
            CloseableHttpClient closeableHttpClient = httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();

            HttpPost httpPost = new HttpPost(url);

            //添加http头信息
            httpPost.addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));
            httpPost.addHeader("Content-Type", "application/json");

            //存入请求参数
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
//                    System.out.println("result===="+result);
//                    System.out.println(result);
                    return result;
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

//    public static String getReal(String url){
//        try {
//            HttpClient client = new HttpClient();
//            HttpMethod method = new GetMethod(url);
//            HttpParams params = client.getParams();
//            params.setParameter(AllClientPNames.HANDLE_REDIRECTS, false);
//            client.executeMethod(method);
//
//
//            String realUrl = method.getURI().getURI();
//            System.out.println("获取真实的地址成功："+realUrl+",原地址是:"+url);
//            return realUrl;
//        } catch (Exception e) {
//            System.out.println("获取地址失败，原来的地址是：%s"+url);
//            e.printStackTrace();
//            return "";
//        }
//    }

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre1.8.0_181\\lib\\security\\jssecacerts");
//        Map<String, Object> map = new HashMap<>();
//        String s = HttpClientUtil.doPost(url, map);
//        System.out.println(s);

    }
}
