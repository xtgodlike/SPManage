package com.qy.sp.manage.dto.sta;

public class RunDailyEntity extends StatisticBaseEntity {
	
	private int productId; 	// 计费点
	private Double 	amountTotal = 0.0;			//总金额
	private Double 	amountUnfinished = 0.0;	//未完成金额
	private Double 	amountNotUp = 0.0;			// 未上行金额
	private Double 	amountFail = 0.0;				//失败金额
	private Double 	amountSuccess = 0.0;		//扣量前成功金额
	private Double		amountChannel = 0.0;		//扣量后成功金额
	private Double		amountDeducted = 0.0;	//扣量金额
	private Double		ratePreDeducted = 0.0;	//扣量前成功率
	private Double		rateDeducted = 0.0;		//扣量后成功率
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Double getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}
	public Double getAmountUnfinished() {
		return amountUnfinished;
	}
	public void setAmountUnfinished(Double amountUnfinished) {
		this.amountUnfinished = amountUnfinished;
	}
	public Double getAmountNotUp() {
		return amountNotUp;
	}
	public void setAmountNotUp(Double amountNotUp) {
		this.amountNotUp = amountNotUp;
	}
	public Double getAmountFail() {
		return amountFail;
	}
	public void setAmountFail(Double amountFail) {
		this.amountFail = amountFail;
	}
	public Double getAmountSuccess() {
		return amountSuccess;
	}
	public void setAmountSuccess(Double amountSuccess) {
		this.amountSuccess = amountSuccess;
	}
	public Double getAmountChannel() {
		return amountChannel;
	}
	public void setAmountChannel(Double amountChannel) {
		this.amountChannel = amountChannel;
	}
	public Double getAmountDeducted() {
		return amountDeducted;
	}
	public void setAmountDeducted(Double amountDeducted) {
		this.amountDeducted = amountDeducted;
	}
	public Double getRatePreDeducted() {
		return ratePreDeducted;
	}
	public void setRatePreDeducted(Double ratePreDeducted) {
		this.ratePreDeducted = ratePreDeducted;
	}
	public Double getRateDeducted() {
		return rateDeducted;
	}
	public void setRateDeducted(Double rateDeducted) {
		this.rateDeducted = rateDeducted;
	}
	
}
