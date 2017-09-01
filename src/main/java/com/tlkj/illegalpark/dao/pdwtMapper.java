package com.tlkj.illegalpark.dao;

import com.tlkj.illegalpark.entity.*;

public interface pdwtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(pdwt record);

    int insertSelective(pdwt record);

    pdwt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(pdwt record);

    int updateByPrimaryKey(pdwt record);
}