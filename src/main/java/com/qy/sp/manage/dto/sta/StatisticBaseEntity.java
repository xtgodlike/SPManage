package com.qy.sp.manage.dto.sta;

import java.util.Calendar;
import java.util.Date;

public class StatisticBaseEntity {
	private Date 	compDate;
	private String pipleNumber;			//通道编号
	private String 	pipleName;
	private String	channelName;
	
	public void setCompDate(Date date){
		this.compDate = date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channelName == null) ? 0 : channelName.hashCode());
		result = prime * result + ((compDate == null) ? 0 : compDate.hashCode());
		result = prime * result + ((pipleName == null) ? 0 : pipleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticBaseEntity other = (StatisticBaseEntity) obj;
		if (channelName == null) {
			if (other.channelName != null)
				return false;
		} else if (!channelName.equals(other.channelName))
			return false;
		if (compDate == null) {
			if (other.compDate != null)
				return false;
		} else if (!compDate.equals(other.compDate))
			return false;
		if (pipleName == null) {
			if (other.pipleName != null)
				return false;
		} else if (!pipleName.equals(other.pipleName))
			return false;
		return true;
	}

	public Date getCompDate(){
		return this.compDate;
	}
	public String getPipleNumber() {
		return pipleNumber;
	}

	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber;
	}

	public void setPipleName(String pipleName){
		this.pipleName = pipleName;
	}
	public String getPipleName(){
		return this.pipleName;
	}
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
	public String getChannelName(){
		return this.channelName;
	}
	
	public void setStatisticBaseEntity(StatisticBaseEntity entity){
		this.compDate = entity.getCompDate();
		this.pipleName = entity.getPipleName();
		this.channelName = entity.getChannelName();
		this.pipleNumber = entity.getPipleNumber();
	}
	
	public StatisticBaseEntity getStatisticBaseEntity(){
		StatisticBaseEntity entity = new StatisticBaseEntity();
		entity.setChannelName(this.channelName);
		entity.setCompDate(this.compDate);
		entity.setPipleName(this.pipleName);
		entity.setPipleNumber(this.pipleNumber);
		return entity;
	}	
	
}
