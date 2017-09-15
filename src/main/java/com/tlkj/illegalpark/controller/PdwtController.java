package com.tlkj.illegalpark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlkj.illegalpark.entity.pdwt;
import com.tlkj.illegalpark.service.pdwtService;
import com.tlkj.illegalpark.util.JsonUtil;

@Controller
public class PdwtController {
	private pdwtService mPdwtService;
	public pdwtService getmPdwtService() {
		return mPdwtService;
	}
	@Autowired
	public void setmPdwtService(pdwtService mPdwtService) {
		this.mPdwtService = mPdwtService;
	}
	private JsonUtil jsonutil = new JsonUtil();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("getAllpdwtInformation.json")
	public void getAllpdwtInformation(HttpServletRequest request, HttpServletResponse response){
		List<pdwt> pdwtList = mPdwtService.getAllpdwtInformation();
		Map map = new HashMap();
		String jsondata = jsonutil.ListToJSON(pdwtList);
		map.put("pdwtList", jsondata);
		// 获取包数
		List<Integer> pacList = removeDuplicate(pdwtList);
		
		String pacjson = jsonutil.ListToJSON(pacList);
		map.put("pacList", pacjson);
		map.put("maxpac", Collections.max(pacList));
		System.out.println(jsonutil.MapToJSON(map));
		System.out.println("包数："+pacList.size());
		System.out.println("最大包数："+Collections.max(pacList));
		for (int i = 0; i < pacList.size(); i++) {
			System.out.println("包："+pacList.get(i));
		}
		outputJson(response, jsonutil.MapToJSON(map));
		response.setStatus(HttpServletResponse.SC_OK);
	}
	//提取包
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List<pdwt> list){
		List<Integer> listTemp = new ArrayList();
		for (int i = 0; i <list.size(); i++) {
			if(!listTemp.contains(Integer.valueOf(list.get(i).getPac()))){
				listTemp.add(Integer.valueOf(list.get(i).getPac()));
			}
		}
		return listTemp;
	}
	private void outputJson(HttpServletResponse response, String result) {
		response.setContentType("text/javascript;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			try {
				out.write(result);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
