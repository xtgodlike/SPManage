package com.qy.sp.manage.dto;


public class TStaSdkDaily extends TStaSdkDailyKey {
    private Integer startUserNum;

    private Integer addUserNum;

    private Integer payreqUserNum;

    private Integer paysucUserNum;

    private Integer uniPuserNum;
    
    private Integer uniPSuserNum;

    private Double uniPaysucRatio;

    private Integer cmPuserNum;
    
    private Integer cmPSuserNum;

    private Double cmPaysucRatio;

    private Integer telePuserNum;
    
    private Integer telePSuserNum;

    private Double telePaysucRatio;

    private Double infoFee;
    
    private Double payRatio;

    private Double paySucRatio;

    private Double translateRatio;

    private Double arpu;
    
    private String appName;

    private String channelName;

    private String provinceName; 
    
    private String cmPaysucRatioStr;
    private String telePaysucRatioStr;
    private String payRatioStr;
    private String paySucRatioStr;
    private String translateRatioStr;
    private String arpuStr;

    
    public Integer getUniPSuserNum() {
		return uniPSuserNum;
	}

	public void setUniPSuserNum(Integer uniPSuserNum) {
		this.uniPSuserNum = uniPSuserNum;
	}

	public Integer getCmPSuserNum() {
		return cmPSuserNum;
	}

	public void setCmPSuserNum(Integer cmPSuserNum) {
		this.cmPSuserNum = cmPSuserNum;
	}

	public Integer getTelePSuserNum() {
		return telePSuserNum;
	}

	public void setTelePSuserNum(Integer telePSuserNum) {
		this.telePSuserNum = telePSuserNum;
	}

	public Integer getStartUserNum() {
        return startUserNum;
    }

    public void setStartUserNum(Integer startUserNum) {
        this.startUserNum = startUserNum;
    }

    public Integer getAddUserNum() {
        return addUserNum;
    }

    public void setAddUserNum(Integer addUserNum) {
        this.addUserNum = addUserNum;
    }

    public Integer getPayreqUserNum() {
        return payreqUserNum;
    }

    public void setPayreqUserNum(Integer payreqUserNum) {
        this.payreqUserNum = payreqUserNum;
    }

    public Integer getPaysucUserNum() {
        return paysucUserNum;
    }

    public void setPaysucUserNum(Integer paysucUserNum) {
        this.paysucUserNum = paysucUserNum;
    }

    public Integer getUniPuserNum() {
        return uniPuserNum;
    }

    public void setUniPuserNum(Integer uniPuserNum) {
        this.uniPuserNum = uniPuserNum;
    }

    public Double getUniPaysucRatio() {
        return uniPaysucRatio;
    }

    public void setUniPaysucRatio(Double uniPaysucRatio) {
        this.uniPaysucRatio = uniPaysucRatio;
    }

    public Integer getCmPuserNum() {
        return cmPuserNum;
    }

    public void setCmPuserNum(Integer cmPuserNum) {
        this.cmPuserNum = cmPuserNum;
    }

    public Double getCmPaysucRatio() {
        return cmPaysucRatio;
    }

    public void setCmPaysucRatio(Double cmPaysucRatio) {
        this.cmPaysucRatio = cmPaysucRatio;
    }

    public Integer getTelePuserNum() {
        return telePuserNum;
    }

    public void setTelePuserNum(Integer telePuserNum) {
        this.telePuserNum = telePuserNum;
    }

    public Double getTelePaysucRatio() {
        return telePaysucRatio;
    }

    public void setTelePaysucRatio(Double telePaysucRatio) {
        this.telePaysucRatio = telePaysucRatio;
    }

    public Double getInfoFee() {
        return infoFee;
    }

    public void setInfoFee(Double infoFee) {
        this.infoFee = infoFee;
    }

    public Double getPayRatio() {
		return payRatio;
	}

	public void setPayRatio(Double payRatio) {
		this.payRatio = payRatio;
	}

	public Double getPaySucRatio() {
        return paySucRatio;
    }

    public void setPaySucRatio(Double paySucRatio) {
        this.paySucRatio = paySucRatio;
    }

    public Double getTranslateRatio() {
        return translateRatio;
    }

    public void setTranslateRatio(Double translateRatio) {
        this.translateRatio = translateRatio;
    }

    public Double getArpu() {
        return arpu;
    }

    public void setArpu(Double arpu) {
        this.arpu = arpu;
    }

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

	public String getUniPaysucRatioStr() {
		return this.uniPaysucRatio==0?"0":(this.uniPaysucRatio*100+"%");
	}

	public void setUniPaysucRatioStr(String uniPaysucRatioStr) {
	}

	public String getCmPaysucRatioStr() {
		return cmPaysucRatioStr= this.telePaysucRatio==0?"0":(this.telePaysucRatio*100+"%");
	}

	public void setCmPaysucRatioStr(String cmPaysucRatioStr) {
		this.cmPaysucRatioStr = cmPaysucRatioStr;
	}

	public String getTelePaysucRatioStr() {
		return telePaysucRatioStr = this.telePaysucRatio==0?"0":(this.telePaysucRatio*100+"%");
	}

	public void setTelePaysucRatioStr(String telePaysucRatioStr) {
		this.telePaysucRatioStr = telePaysucRatioStr;
	}

	public String getPayRatioStr() {
		return payRatioStr = this.payRatio==0?"0":(this.payRatio*100+"%");
	}

	public void setPayRatioStr(String payRatioStr) {
		this.payRatioStr = payRatioStr;
	}

	public String getPaySucRatioStr() {
		return paySucRatioStr = this.paySucRatio==0?"0":(this.paySucRatio*100+"%");
	}

	public void setPaySucRatioStr(String paySucRatioStr) {
		this.paySucRatioStr = paySucRatioStr;
	}

	public String getTranslateRatioStr() {
		return translateRatioStr = this.translateRatio==0?"0":(this.translateRatio*100+"%");
	}

	public void setTranslateRatioStr(String translateRatioStr) {
		this.translateRatioStr = translateRatioStr;
	}

	public String getArpuStr() {
		return arpuStr =  this.arpu==0?"0":(this.arpu*100+"%");
	}

	public void setArpuStr(String arpuStr) {
		this.arpuStr = arpuStr;
	}

}