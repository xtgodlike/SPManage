package com.qy.sp.manage.dto;

public class TChannelProvinceLimit extends TChannelProvinceLimitKey {
    private Integer tradeDay;

    private Integer tradeMonth;
    
    private Integer opStatus;
    private String pipleName;
    private String channelName;
    private String provinceName;

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

	public String getPipleName() {
		return pipleName;
	}

	public void setPipleName(String pipleName) {
		this.pipleName = pipleName;
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
    
}