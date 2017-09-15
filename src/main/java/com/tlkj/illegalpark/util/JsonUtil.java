package com.tlkj.illegalpark.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.tlkj.illegalpark.entity.pdwt;

public class JsonUtil {
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * JSON数据转换为pdwt列表
	 */
	public ArrayList<pdwt> StringFromJSON(String jsondata){
		JavaType javatype = getCollectionType(ArrayList.class, pdwt.class);
		ArrayList<pdwt> pdwtList = null;
		try {
			pdwtList = mapper.readValue(jsondata, javatype);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pdwtList;
	}
	/**
	 * pdwt数据转换为JSON数据
	 */
	@SuppressWarnings("rawtypes")
	public String ListToJSON(List list){
		try {
			return mapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private JavaType getCollectionType(Class<?> class1, Class<?> class2) {
		return mapper.getTypeFactory().constructParametricType(class1, class2);
	}
	
	@SuppressWarnings("rawtypes")
	public String MapToJSON(Map map){
		try {
			return mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
