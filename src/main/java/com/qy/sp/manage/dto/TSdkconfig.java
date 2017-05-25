package com.qy.sp.manage.dto;

import java.util.Date;

public class TSdkconfig {
    private String configId;

	private String configDescription;

	private Date createTime;

	private Date modTime;
	
	private String globalConfigValue;

	public String getGlobalConfigValue() {
		return globalConfigValue;
	}

	public void setGlobalConfigValue(String globalConfigValue) {
		this.globalConfigValue = globalConfigValue;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId == null ? null : configId.trim();
	}

	public String getConfigDescription() {
		return configDescription;
	}

	public void setConfigDescription(String configDescription) {
		this.configDescription = configDescription == null ? null
				: configDescription.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

}