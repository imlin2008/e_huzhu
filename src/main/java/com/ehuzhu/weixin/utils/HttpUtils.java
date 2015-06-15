package com.ehuzhu.weixin.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	
	/**
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public  static String httpGet(String url) throws ClientProtocolException, IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        logger.info("Executing request " + httpGet.getRequestLine());
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
        	int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status+",url:"+url);
            }
        } finally {
        	response.close();
            httpclient.close();
        }
    }
	
	
	/**
	 * @param url
	 * @param nvps
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	 * nvps.add(new BasicNameValuePair("username", "vip"));
	 * nvps.add(new BasicNameValuePair("password", "secret"));
	 */
	public  static String httpPost(String url,List <NameValuePair> nvps) throws ClientProtocolException, IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        logger.info("Executing request " + httpPost.getRequestLine());
        try {
        	int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status+",url:"+url);
            }
        } finally {
        	response.close();
            httpclient.close();
        }
    }
}
