package com.qy.sp.manage.dto.sta;

import java.io.Serializable;
import java.util.Date;

public class StaticEntity implements Serializable {
	
	private static final long serialVersionUID = 8555992578400429994L;
	private Date 	compDate;      		//日期
	private String pipleNumber;			//通道编号
	private String  pipleName;			//通道名称
	private String  channelName;		//渠道名称
	private String provinceName;		// 省份名称
	private Double 	amountTotal;		//总金额
	private Integer	countToltal;		//总条数
	
	private Double 	amountW;		// 未完成金额
	private Integer	countW;		// 未完成条数
	private Double 	amountF;		// 失败金额
	private Integer	countF;		// 失败条数
	
	private Double 	amountChannel;		//扣量后成功金额
	private Integer	countChannel;		//扣量后成功条数
	private Double 	amountDeducted;     //扣量金额
	private Integer	countDeducted;		//扣量条数
	
	private String compDateString;	
	private String successRatePreDeducted;		//扣量前成功率
	private String successRateAfterDeducted;	//扣量后成功率
	
	public void setSuccessRatePreDeducted(String rate){
		this.successRatePreDeducted = rate;
	}
	public String getSuccessRatePreDeducted(){
		return this.successRatePreDeducted;
	}
	public void setSuccessRateAfterDeducted(String rate){
		this.successRateAfterDeducted = rate;
	}
	public String getSuccessRateAfterDeducted(){
		return this.successRateAfterDeducted;
	}
	public void setCompDateString(String date){
		this.compDateString = date;
	}
	public String getCompDateString(){
		return this.compDateString;
	}
	public Date getCompDate() {
		return compDate;
	}
	public void setCompDate(Date compDate) {
		this.compDate = compDate;
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
	public Double getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}
	public Integer getCountToltal() {
		return countToltal;
	}
	public void setCountToltal(Integer countToltal) {
		this.countToltal = countToltal;
	}
	public Double getAmountChannel() {
		return amountChannel;
	}
	public void setAmountChannel(Double amountChannel) {
		this.amountChannel = amountChannel;
	}
	public Integer getCountChannel() {
		return countChannel;
	}
	public void setCountChannel(Integer countChannel) {
		this.countChannel = countChannel;
	}
	public Double getAmountDeducted() {
		return amountDeducted;
	}
	public void setAmountDeducted(Double amountDeducted) {
		this.amountDeducted = amountDeducted;
	}
	public Integer getCountDeducted() {
		return countDeducted;
	}
	public void setCountDeducted(Integer countDeducted) {
		this.countDeducted = countDeducted;
	}
	public String getPipleNumber() {
		return pipleNumber;
	}
	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber;
	}
	public Double getAmountW() {
		return amountW;
	}
	public void setAmountW(Double amountW) {
		this.amountW = amountW;
	}
	public Integer getCountW() {
		return countW;
	}
	public void setCountW(Integer countW) {
		this.countW = countW;
	}
	public Double getAmountF() {
		return amountF;
	}
	public void setAmountF(Double amountF) {
		this.amountF = amountF;
	}
	public Integer getCountF() {
		return countF;
	}
	public void setCountF(Integer countF) {
		this.countF = countF;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}
