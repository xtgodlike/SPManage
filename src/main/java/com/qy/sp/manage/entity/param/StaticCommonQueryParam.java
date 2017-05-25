package com.qy.sp.manage.entity.param;

import java.io.Serializable;
import java.util.Date;

public class StaticCommonQueryParam implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3417864865013996719L;
	private String pipleNumber;
	private String 	pipleId;
	private String 	channelId;
	private Date	startDate;
	private Date	endDate;
	
	private String 	userId;

	public String getPipleNumber() {
		return pipleNumber;
	}

	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber;
	}

	public String getPipleId() {
		return pipleId;
	}

	public void setPipleId(String pipleId) {
		this.pipleId = pipleId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
