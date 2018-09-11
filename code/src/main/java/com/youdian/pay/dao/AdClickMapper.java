package com.youdian.pay.dao;

import com.youdian.pay.dao.model.AdClick;
import com.youdian.pay.dao.model.AdClickExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdClickMapper {
    long countByExample(AdClickExample example);

    int deleteByExample(AdClickExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdClick record);

    int insertSelective(AdClick record);

    List<AdClick> selectByExample(AdClickExample example);

    AdClick selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdClick record, @Param("example") AdClickExample example);

    int updateByExample(@Param("record") AdClick record, @Param("example") AdClickExample example);

    int updateByPrimaryKeySelective(AdClick record);

    int updateByPrimaryKey(AdClick record);
}