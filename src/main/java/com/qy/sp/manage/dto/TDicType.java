package com.qy.sp.manage.dto;

public class TDicType {
    private Integer dtypeId;

    private String dtypeName;

    public Integer getDtypeId() {
        return dtypeId;
    }

    public void setDtypeId(Integer dtypeId) {
        this.dtypeId = dtypeId;
    }

    public String getDtypeName() {
        return dtypeName;
    }

    public void setDtypeName(String dtypeName) {
        this.dtypeName = dtypeName == null ? null : dtypeName.trim();
    }
}