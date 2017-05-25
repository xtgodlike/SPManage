package com.qy.sp.manage.dto;

public class TSdkconfigPhone extends TSdkconfigPhoneKey {
    private String configValue;

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }
}