package com.qy.sp.manage.dto;

public class TSdkconfigCross extends TSdkconfigCrossKey {
    private String configValue;
    private String appName;
    private String channelName;
    private String provinceName;
    private String apiKey;
    private String pipleName;
    private String pipleNumber;

    public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
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
    
}