package com.tlkj.illegalpark.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tlkj.illegalpark.entity.*;
@Repository
public interface pdwtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(pdwt record);

    int insertSelective(pdwt record);

    pdwt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(pdwt record);

    int updateByPrimaryKey(pdwt record);
    
    List<pdwt> getAllpdwtInformation();
}