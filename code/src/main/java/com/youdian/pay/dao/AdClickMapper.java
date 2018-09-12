package com.youdian.pay.dao;

import com.youdian.pay.dao.model.AdClick;
import com.youdian.pay.dao.model.AdClickExample;
import java.util.List;

public interface AdClickMapper {
    int deleteByExample(AdClickExample example);

    int insert(AdClick record);

    List<AdClick> selectByExample(AdClickExample example);

    AdClick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdClick record);
}