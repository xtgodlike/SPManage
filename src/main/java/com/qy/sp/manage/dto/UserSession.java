package com.qy.sp.manage.dto;

import java.io.Serializable;


public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String chnName;
	private int level;
	private String cPID;
	private String channelID;
	private int userType;
	private int layoutId; // 布局ID
	
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getChnName() {
		return chnName;
	}

	public void setChnName(String chnName) {
		this.chnName = chnName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getcPID() {
		return cPID;
	}

	public void setcPID(String cPID) {
		this.cPID = cPID;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

	public void init(TUser user){
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.chnName = user.getChnName();
		this.level = user.getLevel();
		this.cPID = user.getCpId();
		this.channelID = user.getChannelId();
		this.userType = user.getUserType();
	}
}
