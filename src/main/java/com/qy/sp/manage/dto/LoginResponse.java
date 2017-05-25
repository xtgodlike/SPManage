package com.qy.sp.manage.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private String resultMessage;
	private TUser user;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}
}
