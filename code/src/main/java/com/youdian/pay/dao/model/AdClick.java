package com.youdian.pay.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class AdClick implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getAdSeatId() {
        return adSeatId;
    }

    public void setAdSeatId(Integer adSeatId) {
        this.adSeatId = adSeatId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobileOs() {
        return mobileOs;
    }

    public void setMobileOs(String mobileOs) {
        this.mobileOs = mobileOs;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AdClick other = (AdClick) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAdId() == null ? other.getAdId() == null : this.getAdId().equals(other.getAdId()))
            && (this.getAdSeatId() == null ? other.getAdSeatId() == null : this.getAdSeatId().equals(other.getAdSeatId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getMobileOs() == null ? other.getMobileOs() == null : this.getMobileOs().equals(other.getMobileOs()))
            && (this.getMobileModel() == null ? other.getMobileModel() == null : this.getMobileModel().equals(other.getMobileModel()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getNetType() == null ? other.getNetType() == null : this.getNetType().equals(other.getNetType()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAdId() == null) ? 0 : getAdId().hashCode());
        result = prime * result + ((getAdSeatId() == null) ? 0 : getAdSeatId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMobileOs() == null) ? 0 : getMobileOs().hashCode());
        result = prime * result + ((getMobileModel() == null) ? 0 : getMobileModel().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getNetType() == null) ? 0 : getNetType().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", adId=").append(adId);
        sb.append(", adSeatId=").append(adSeatId);
        sb.append(", createTime=").append(createTime);
        sb.append(", mobileOs=").append(mobileOs);
        sb.append(", mobileModel=").append(mobileModel);
        sb.append(", ip=").append(ip);
        sb.append(", netType=").append(netType);
        sb.append(", shopId=").append(shopId);
        sb.append(", source=").append(source);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}