package com.tlkj.illegalpark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tlkj.illegalpark.entity.pdwt;

@Service
public interface pdwtService {
	pdwt selectByPrimaryKey(Integer id);
	List<pdwt> getAllpdwtInformation();
}
