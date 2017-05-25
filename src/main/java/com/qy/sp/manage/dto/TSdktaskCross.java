package com.qy.sp.manage.dto;

public class TSdktaskCross extends TSdktaskCrossKey {
    private Integer taskExecute;
    private String appName;
    private String channelName;
    private String provinceName;
    private String stepDesc;
    public Integer getTaskExecute() {
        return taskExecute;
    }

    public void setTaskExecute(Integer taskExecute) {
        this.taskExecute = taskExecute;
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

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}
	
}