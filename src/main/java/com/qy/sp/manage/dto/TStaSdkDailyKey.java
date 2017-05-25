package com.qy.sp.manage.dto;

import java.util.Date;

import com.qy.sp.manage.common.utils.DateTimeUtils;

public class TStaSdkDailyKey {
    private Date compDate;

    private String appId;

    private String channelId;

    private Integer provinceId;

    private String compDateStr;
    public Date getCompDate() {
        return compDate;
    }

    public void setCompDate(Date compDate) {
        this.compDate = compDate;
    }
    
	public String getCompDateStr() {
		return compDateStr = DateTimeUtils.formatTime(this.compDate, DateTimeUtils.yyyyMMdd);
	}

	public void setCompDateStr(String compDateStr) {
		this.compDateStr = compDateStr;
	}

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}