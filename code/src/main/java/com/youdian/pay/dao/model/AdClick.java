/*
 * Dian.so Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package com.youdian.pay.dao.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO
 *
 * @author MBG插件生成
 * @version $Id: AdClick.java, v 0.1 2018-09-12 23:39:06 Exp $
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdClick extends BaseModel<AdClick> implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 178509440772869488L;

    private Integer id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 广告id
     */
    private Integer adId;

    /**
     * 广告位id
     */
    private Integer adSeatId;

    /**
     * 点击时间
     */
    private Date createTime;

    /**
     * 手机系统
     */
    private String mobileOs;

    /**
     * 手机型号
     */
    private String mobileModel;

    /**
     * 手机ip
     */
    private String ip;

    /**
     * 联网方式
     */
    private String netType;

    /**
     * 门店id
     */
    private String shopId;

    /**
     * 来源渠道
     */
    private Integer source;

    /**
     * 更新时间
     */
    private Date updateTime;
}