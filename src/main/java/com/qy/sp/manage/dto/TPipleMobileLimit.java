package com.qy.sp.manage.dto;

public class TPipleMobileLimit {
    private String pipleId;

    private Integer tradeDay;

    private Integer tradeMonth;
    
    private Integer successNumDay;
    private Integer successNumMonth;
    
    private Integer reqNumDay;
    private Integer reqNumMonth;

    public String getPipleId() {
        return pipleId;
    }

    public void setPipleId(String pipleId) {
        this.pipleId = pipleId == null ? null : pipleId.trim();
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

	public Integer getSuccessNumDay() {
		return successNumDay;
	}

	public void setSuccessNumDay(Integer successNumDay) {
		this.successNumDay = successNumDay;
	}

	public Integer getSuccessNumMonth() {
		return successNumMonth;
	}

	public void setSuccessNumMonth(Integer successNumMonth) {
		this.successNumMonth = successNumMonth;
	}

	public Integer getReqNumDay() {
		return reqNumDay;
	}

	public void setReqNumDay(Integer reqNumDay) {
		this.reqNumDay = reqNumDay;
	}

	public Integer getReqNumMonth() {
		return reqNumMonth;
	}

	public void setReqNumMonth(Integer reqNumMonth) {
		this.reqNumMonth = reqNumMonth;
	}
    
}