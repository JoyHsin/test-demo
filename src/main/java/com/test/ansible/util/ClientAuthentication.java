package com.test.ansible.util;


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientAuthentication {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre1.8.0_181\\lib\\security\\jssecacerts");

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("httpbin.org", 80),
                new UsernamePasswordCredentials("admin", "tower@123"));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        try {
//            HttpGet httpget = new HttpGet("https://10.119.85.113/api/v2/job_templates/7/launch/");
            HttpPost httpPost = new HttpPost("https://10.119.85.113:443/api/v2/hosts");
            System.out.println("Executing request " + httpPost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
