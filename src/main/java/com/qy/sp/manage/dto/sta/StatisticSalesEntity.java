package com.qy.sp.manage.dto.sta;

public class StatisticSalesEntity extends StatisticBaseEntity{
	private Integer	orderStatus;
	private Integer	deductedStatus;
	private Double	amount;
	private Integer count;
	
	private Double pipleSetRatio;
	private Double channelSetRatio;
	
	public void setOrderStatus(Integer status){
		this.orderStatus = status;
	}
	public Integer getOrderStatus(){
		return this.orderStatus;
	}
	public void setDeductedStatus(Integer status){
		this.deductedStatus = status;
	}
	public Integer getDeductedStatus(){
		return this.deductedStatus;
	}
	public void setAmount(Double amount){
		this.amount = amount;
	}
	public Double getAmount(){
		return this.amount==null?0:this.amount;
	}
	public void setCount(Integer count){
		this.count = count;
	}
	public Integer getCount(){
		return this.count;
	}
	public void setChannelSetRatio(double channelSetRatio) {
		this.channelSetRatio = channelSetRatio;
	}
	public Double getPipleSetRatio() {
		return pipleSetRatio;
	}
	public void setPipleSetRatio(Double pipleSetRatio) {
		this.pipleSetRatio = pipleSetRatio;
	}
	public Double getChannelSetRatio() {
		return channelSetRatio;
	}
	public void setChannelSetRatio(Double channelSetRatio) {
		this.channelSetRatio = channelSetRatio;
	}
	
}
