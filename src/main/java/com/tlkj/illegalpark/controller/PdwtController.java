package com.tlkj.illegalpark.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tlkj.illegalpark.entity.pdwt;
import com.tlkj.illegalpark.entity.pdwtsearch;
import com.tlkj.illegalpark.service.pdwtService;
import com.tlkj.illegalpark.util.JsonUtil;
import com.tlkj.illegalpark.util.MapUtil;

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
	public void getAllpdwtInformation(HttpServletRequest request, HttpServletResponse response) {
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
		System.out.println("包数：" + pacList.size());
		System.out.println("最大包数：" + Collections.max(pacList));
		for (int i = 0; i < pacList.size(); i++) {
			System.out.println("包：" + pacList.get(i));
		}
		System.out.println(pacList);
		outputJson(response, jsonutil.MapToJSON(map));
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@RequestMapping("getAllSearchInformation.json")
	public void getAllSearchInformation(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String keywords = new String(request.getParameter("keywords").getBytes("iso8859-1"), "utf-8");
		System.out.println(keywords);
		List<pdwt> pdwtList = mPdwtService.searchpdwtInformation(keywords, "%" + keywords + "%", keywords);
		List<pdwtsearch> pdwtsearchList = new ArrayList<pdwtsearch>();
		;
		pdwtsearch ps = null;
		System.out.println(pdwtList.size());
		for (int i = 0; i < pdwtList.size(); i++) {
			ps = new pdwtsearch();
			ps.setAddress(pdwtList.get(i).getInstalllocation());
			ps.setId(pdwtList.get(i).getDeviceaccessid());
			String coordsys = pdwtList.get(i).getLng() + "," + pdwtList.get(i).getLat();
			String Convertcoordsys = MapUtil.convert(coordsys);
			// System.out.println(Convertcoordsys);
			String[] strs = Convertcoordsys.split(",");
			ps.setLatitude(strs[1]);
			ps.setLongitude(strs[0]);
			ps.setName(pdwtList.get(i).getEntrynumber());
			ps.setPrice("none");
			ps.setRating("none");
			ps.setPic("none");
			pdwtsearchList.add(ps);
			// System.out.println(pdwtsearchList.size());
		}
		String jsondata = jsonutil.ListToJSON(pdwtsearchList);
		outputJson(response, jsondata);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	// 提取包
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List removeDuplicate(List<pdwt> list) {
		List<Integer> listTemp = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if (!listTemp.contains(Integer.valueOf(list.get(i).getPac()))) {
				listTemp.add(Integer.valueOf(list.get(i).getPac()));
			}
		}
		return listTemp;
	}

	// 图片上传
	@RequestMapping("UploadImageServlet.json")
	public void UploadImageServlet(HttpServletRequest request, HttpServletResponse response) {
		String message = "success";
		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dff);
			List<FileItem> items = sfu.parseRequest(request);
			System.out.println("items:" + items.size());
			// 获取上传字段
			FileItem fileItem = items.get(0);
			// 获取文件名
			String imageName = fileItem.getName();
			System.out.println("filename:" + imageName);
			String[] aa = imageName.split("/");
			if (imageName != null) {
				// filename = IdGenertor.generateGUID() + "." +
				// FilenameUtils.getExtension(filename);
				imageName = aa[aa.length - 1];
			}
			// 生成存储路径
			String storeDirectory = "E:\\myImage\\";
			System.out.println("storeDirectory:" + storeDirectory);
			File file = new File(storeDirectory);
			if (!file.exists()) {
				file.mkdir();
			}
			//String path = genericPath(imageName, storeDirectory);
			// 处理文件的上传
			try {
				fileItem.write(new File(storeDirectory, imageName));

				String filePath = "/myImage/" + imageName;
				System.out.println("filePath:" + filePath);
				message = filePath;
			} catch (Exception e) {
				message = "上传图片失败";
			}
		} catch (FileUploadException e) {
			message = "上传图片失败";
			e.printStackTrace();
		}
		outputJson(response, message);
		response.setStatus(HttpServletResponse.SC_OK);
	}

//	// 计算文件的存放目录
//	private String genericPath(String filename, String storeDirectory) {
//
////		File file = new File(storeDirectory, filename);
////		if (!file.exists()) {
////			file.mkdirs();
////		}
////		return dir;
//	}

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
