package com.moneylocker.account.bean;

import java.io.Serializable;
import java.util.Date;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = -2756837502239884530L;

	private String userId;

	private Date loginTime;

	public LoginInfo() {

	}

	public LoginInfo(String userId ) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
