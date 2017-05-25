package com.qy.sp.manage.dto;

public class TPipleProduct extends TPipleProductKey {
    private String pipleProductCode;

    private String pipleProductAbbrCode;
    
    private String productCode;
    private String productName;

    private Integer opStatus;

    public String getPipleProductCode() {
        return pipleProductCode;
    }

    public void setPipleProductCode(String pipleProductCode) {
        this.pipleProductCode = pipleProductCode == null ? null : pipleProductCode.trim();
    }

    public String getPipleProductAbbrCode() {
        return pipleProductAbbrCode;
    }

    public void setPipleProductAbbrCode(String pipleProductAbbrCode) {
        this.pipleProductAbbrCode = pipleProductAbbrCode == null ? null : pipleProductAbbrCode.trim();
    }

    public Integer getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(Integer opStatus) {
        this.opStatus = opStatus;
    }

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
    
}