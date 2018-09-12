/*
 * Dian.so Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.youdian.pay.dao;

import com.youdian.pay.dao.model.AdClick;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 有BaseDAO，可以把以下四个方法删除
 *
 * @author MBG插件生成
 * @version $Id: AdClickDAO.java, v 0.1 2018-09-12 23:39:06 Exp $
 */
@Mapper
public interface AdClickDAO extends BaseDAO<AdClick> {
    List<AdClick> listRecord(AdClick model);

    AdClick getRecord(AdClick model);

    int saveRecord(AdClick model);

    int removeRecord(AdClick model);

    int updateRecord(AdClick model);
}