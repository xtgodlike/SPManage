package com.qy.sp.manage.dto;

public class PiplePriority {
	
	private Integer hostId;
	private Integer provinceId;
	private String pipleId;
	private String hostName;
	private String provinceName;
	private String pipleName;
	private Integer priority;
	private String pipleNumber;
	private String pipleType;
	private Integer opStatus;
	
	public Integer getHostId() {
		return hostId;
	}
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getPipleId() {
		return pipleId;
	}
	public void setPipleId(String pipleId) {
		this.pipleId = pipleId;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getPipleName() {
		return pipleName;
	}
	public void setPipleName(String pipleName) {
		this.pipleName = pipleName;
	}
	public String getPipleNumber() {
		return pipleNumber;
	}
	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber;
	}
	public String getPipleType() {
		return pipleType;
	}
	public void setPipleType(String pipleType) {
		this.pipleType = pipleType;
	}
	public Integer getOpStatus() {
		return opStatus;
	}
	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
	}

}
