package com.test.ansible.util;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpTest {

    public static String httpGet(String httpUrl) {
        BufferedReader input = null;
        StringBuilder sb = null;
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(httpUrl);
            try {
                // trust all hosts
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
                if (url.getProtocol().toLowerCase().equals("https")) {
                    https.setHostnameVerifier(DO_NOT_VERIFY);
                    con = https;
                } else {
                    con = (HttpURLConnection)url.openConnection();
                }
                input = new BufferedReader(new InputStreamReader(con.getInputStream()));
                sb = new StringBuilder();
                String s;
                while ((s = input.readLine()) != null) {
                    sb.append(s).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } finally {
            // close buffered
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}

            // disconnecting releases the resources held by a connection so they may be closed or reused
            if (con != null) {
                con.disconnect();
            }
        }
        return sb == null ? null : sb.toString();
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Log.i(TAG, "checkClientTrusted");
                System.out.println(TAG+"checkClientTrusted");
            }
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                Log.i(TAG, "checkServerTrusted");
                System.out.println(TAG+"checkServerTrusted");
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
