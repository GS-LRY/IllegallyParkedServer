package com.tlkj.illegalpark.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tlkj.illegalpark.dao.pdwtMapper;
import com.tlkj.illegalpark.entity.pdwt;
import com.tlkj.illegalpark.service.pdwtService;

@Service
public class pdwtServiceImp implements pdwtService {

	@Autowired
	private pdwtMapper pdwtMapper;
	
	public pdwtMapper getPdwtMapper() {
		return pdwtMapper;
	}
	
	public void setPdwtMapper(pdwtMapper pdwtMapper) {
		this.pdwtMapper = pdwtMapper;
	}

	@Override
	public pdwt selectByPrimaryKey(Integer id) {
		return pdwtMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<pdwt> getAllpdwtInformation() {
		return pdwtMapper.getAllpdwtInformation();
	}

}
