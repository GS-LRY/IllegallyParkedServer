package com.tlkj.illegalpark.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class MapUtil {
	public static String getHttpResponse(String allConfigUrl) {
		BufferedReader in = null;
		StringBuffer result = null;
		try {
			// url请求中如果有中文，要在接收方用相应字符转码
			URI uri = new URI(allConfigUrl);
			URL url = uri.toURL();
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.connect();
			result = new StringBuffer();
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 高德地图WebAPI : gps坐标转化为高德坐标 <br/>
	 * String coordsys：高德地图坐标
	 * 
	 */
	public static String convert(String coordsys) {
		try {
			coordsys = URLEncoder.encode(coordsys, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "http://restapi.amap.com/v3/assistant/coordinate/convert?locations=" + coordsys
				+ "&coordsys=gps&output=json&key=060212638b94290e3dd0648c15753b64";
		JSONObject jsonobject = JSONObject.fromObject(getHttpResponse(url));
		String coordinateString = jsonobject.getString("locations");
		return coordinateString;
	}

}
