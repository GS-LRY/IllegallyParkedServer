package com.tlkj.illegalpark.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientGetTableId {
	private static Logger log = Logger.getLogger(HttpClientGetTableId.class);

	public static String post(String url, Map<String, String> params) {
		String retSrc = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = postForm(url, params);
			CloseableHttpResponse response;
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if(entity!=null){
	        	retSrc = EntityUtils.toString(entity);
//	        	
//	        	JSONObject results = new JSONObject(retSrc);
//	        	String token = results.getString("tableid");
//	        	System.out.println(token);
//	        	return retSrc;
	        }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retSrc;
        
	}

	
	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			log.info("set utf-8 form entity to httppost");
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}
}
