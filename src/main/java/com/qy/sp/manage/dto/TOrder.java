package com.qy.sp.manage.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TOrder implements Cloneable {
    private String orderId;
    private String pipleId;
    private String mobile;
    private String channelId;
    private String productId;
    private Integer orderStatus;
    private Integer subStatus;
    private String pipleOrderId;
    private Date createTime;
    private Date modTime;
    private Date completeTime;
    private Integer decStatus;
    private Integer rnd;
    private String imsi;
    private String iccid;
    private String resultCode;
    private Integer volt;
    private String  orderGroupId;
    private BigDecimal amount;
    private Integer provinceId;
    private String flowId;
    private String extData;
    
    private String pipleNumber;
    private String pipleName;
    private String channelName;
    private String provinceName;
    
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPipleId() {
		return pipleId;
	}
	public void setPipleId(String pipleId) {
		this.pipleId = pipleId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(Integer subStatus) {
		this.subStatus = subStatus;
	}
	public String getPipleOrderId() {
		return pipleOrderId;
	}
	public void setPipleOrderId(String pipleOrderId) {
		this.pipleOrderId = pipleOrderId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModTime() {
		return modTime;
	}
	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Integer getDecStatus() {
		return decStatus;
	}
	public void setDecStatus(Integer decStatus) {
		this.decStatus = decStatus;
	}
	public Integer getRnd() {
		return rnd;
	}
	public void setRnd(Integer rnd) {
		this.rnd = rnd;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public Integer getVolt() {
		return volt;
	}
	public void setVolt(Integer volt) {
		this.volt = volt;
	}
	public String getOrderGroupId() {
		return orderGroupId;
	}
	public void setOrderGroupId(String orderGroupId) {
		this.orderGroupId = orderGroupId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getExtData() {
		return extData;
	}
	public void setExtData(String extData) {
		this.extData = extData;
	}
	public String getPipleNumber() {
		return pipleNumber;
	}
	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber;
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
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
}