package com.qy.sp.manage.dto;

public class TSdktaskPhoneKey {
    private String taskId;

    private String phoneId;

    private String taskStep;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId == null ? null : phoneId.trim();
    }

    public String getTaskStep() {
        return taskStep;
    }

    public void setTaskStep(String taskStep) {
        this.taskStep = taskStep == null ? null : taskStep.trim();
    }
}