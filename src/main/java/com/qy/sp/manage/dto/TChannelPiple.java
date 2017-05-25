package com.qy.sp.manage.dto;


public class TChannelPiple extends TChannelPipleKey {
    private String notifyUrl;
    private Integer volt;
    private Integer tradeDay;
    private Integer tradeMonth;
    private Integer opStatus;
    private double settlementRatio;
    
    private String channelName;
    private String pipleName;
    private String pipleNumber;
    private String apiKey;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public Integer getVolt() {
        return volt;
    }

    public void setVolt(Integer volt) {
        this.volt = volt;
    }

	public Integer getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(Integer tradeDay) {
		this.tradeDay = tradeDay;
	}

	public Integer getTradeMonth() {
		return tradeMonth;
	}

	public void setTradeMonth(Integer tradeMonth) {
		this.tradeMonth = tradeMonth;
	}

	public Integer getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public double getSettlementRatio() {
		return settlementRatio;
	}

	public void setSettlementRatio(double settlementRatio) {
		this.settlementRatio = settlementRatio;
	}
    
}