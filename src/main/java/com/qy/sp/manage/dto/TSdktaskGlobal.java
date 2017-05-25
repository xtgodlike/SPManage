package com.qy.sp.manage.dto;

public class TSdktaskGlobal extends TSdktaskGlobalKey {
    private Integer taskExecute;
    private String taskName;
    
    public Integer getTaskExecute() {
        return taskExecute;
    }

    public void setTaskExecute(Integer taskExecute) {
        this.taskExecute = taskExecute;
    }

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
    
}