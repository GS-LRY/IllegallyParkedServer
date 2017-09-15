package com.tlkj.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tlkj.illegalpark.entity.pdwt;
import com.tlkj.illegalpark.service.pdwtService;
import com.tlkj.illegalpark.util.JsonUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class TestMybatis {

	private pdwtService mpdwtService;
	private JsonUtil jsonutil = new JsonUtil();

	public pdwtService getMpdwtService() {
		return mpdwtService;
	}

	@Autowired
	public void setMpdwtService(pdwtService mpdwtService) {
		this.mpdwtService = mpdwtService;
	}

	@Test
	public void Test1(){
		pdwt pdwttest = mpdwtService.selectByPrimaryKey(1);
		System.out.println(pdwttest.getInstalllocation());
	}
	@Test
	public void Test2(){
		String jsondata = jsonutil.ListToJSON(mpdwtService.getAllpdwtInformation());
		System.out.println(jsondata);
	}
}
