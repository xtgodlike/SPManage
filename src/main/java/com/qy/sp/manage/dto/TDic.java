package com.qy.sp.manage.dto;

public class TDic {
    private Integer dicId;

    private Integer dtypeId;

    private String dicCode;

    private String dicName;

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public Integer getDtypeId() {
        return dtypeId;
    }

    public void setDtypeId(Integer dtypeId) {
        this.dtypeId = dtypeId;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }
}