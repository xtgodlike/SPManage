package com.qy.sp.manage.dto;

public class TSdkconfigCrossKey {
    private String configId;

    private String appId;

    private String channelId;

    private String provinceId;
    
    private String pipleId;
    
	public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

	public String getPipleId() {
		return pipleId;
	}

	public void setPipleId(String pipleId) {
		this.pipleId = pipleId == null ? null : pipleId.trim();
	}
    
}