package com.qy.sp.manage.dto.sta;

public class SalesDailyEntity extends StatisticBaseEntity implements Comparable<SalesDailyEntity>{
	private Double 	amountTotal = 0.0;			//总金额
	private Double 	amountUnfinished = 0.0;		//未完成金额
	private Double 	amountFail = 0.0;			//失败金额
	private Double	amountSuccess = 0.0;		//总成功金额（扣量前成功金额）
	private Double	amountChannel = 0.0;		//扣量后成功金额
	private Double	amountDeducted = 0.0;		//扣量金额
	private String	ratePreDeducted = "0.0%";	//扣量前成功率
	private String	rateDeducted = "0.0%";		//扣量后成功率
	private String	compDateString;
	
	private Integer countTotal = 0;				//总条数
	private Integer countUnfinished = 0;		//未完成条数
	private Integer countFail = 0;				//失败条数
	private Integer countSuccess = 0;				//总成功条数（扣量前）
	private Integer countChannel = 0;			//成功条数(扣量后)
	private Integer countDeducted = 0;			//扣量条数
	
	private String pipleSetRatioStr  = "0.0%";
	private String channelSetRatioStr  = "0.0%";
	
	public void initTotal(){
		this.amountTotal = this.amountChannel + this.amountDeducted + this.amountFail + this.amountUnfinished;
		this.countTotal = this.countChannel + this.countDeducted + this.countFail + this.countUnfinished;
	}
	public void setCountTotal(Integer count){
		this.countTotal = count;
	}
	public Integer getCountTotal(){
		return this.countTotal;
	}
	public void setCountUnfinished(Integer count){
		this.countUnfinished = count;
	}
	public Integer getCountUnfinished(){
		return this.countUnfinished;
	}
	public void setCountFail(Integer count){
		this.countFail = count;
	}
	public Integer getCountFail(){
		return this.countFail;
	}
	
	public Double getAmountSuccess() {
		return amountSuccess;
	}
	public void setAmountSuccess(Double amountSuccess) {
		this.amountSuccess = amountSuccess;
	}
	public void setCountChannel(Integer count){
		this.countChannel = count;
	}
	public Integer getCountChannel(){
		return this.countChannel;
	}
	public void setCountDeducted(Integer count){
		this.countDeducted = count;
	}
	public Integer getCountDeducted(){
		return this.countDeducted;
	}
	public void setAmountTotal(Double amount){
		this.amountTotal = amount;
	}
	public Double getAmountTotal(){
		return this.amountTotal;
	}
	public void setAmountUnfinished(Double amount){
		this.amountUnfinished = amount;
	}
	public Double getAmountUnfinished(){
		return this.amountUnfinished==null?0:this.amountUnfinished;
	}
	public void setAmountFail(Double amount){
		this.amountFail = amount;
	}
	public Double getAmountFail(){
		return this.amountFail;
	}
	public void setAmountChannel(Double amount){
		this.amountChannel = amount;
	}
	public Double getAmountChannel(){
		return this.amountChannel;
	}
	public void setAmountDeducted(Double amount){
		this.amountDeducted = amount;
	}
	public Double getAmountDeducted(){
		return this.amountDeducted;
	}
	public void setRatePreDeducted(String rate){
		this.ratePreDeducted = rate;
	}
	public String getRatePreDeducted(){
		return this.ratePreDeducted;
	} 
	public void setRateDeducted(String rate){
		this.rateDeducted = rate;
	}
	public String getRateDeducted(){
		return this.rateDeducted;
	}
	public void setCompDateString(String date){
		this.compDateString = date;
	}
	public String getCompDateString(){
		return this.compDateString;
	}
	
	public Integer getCountSuccess() {
		return countSuccess;
	}
	public void setCountSuccess(Integer countSuccess) {
		this.countSuccess = countSuccess;
	}
	public String getPipleSetRatioStr() {
		return pipleSetRatioStr;
	}
	public void setPipleSetRatioStr(String pipleSetRatioStr) {
		this.pipleSetRatioStr = pipleSetRatioStr;
	}
	public String getChannelSetRatioStr() {
		return channelSetRatioStr;
	}
	public void setChannelSetRatioStr(String channelSetRatioStr) {
		this.channelSetRatioStr = channelSetRatioStr;
	}
	@Override
	public int compareTo(SalesDailyEntity o) {
		return this.getCompDateString().compareTo(o.getCompDateString());
	}
}
