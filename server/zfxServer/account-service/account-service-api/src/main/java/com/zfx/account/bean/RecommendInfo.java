package com.moneylocker.account.bean;

import java.io.Serializable;

public class RecommendInfo implements Serializable {

	private static final long serialVersionUID = -6042643500774414292L;

	private int credit;

	private String userId;

	private String phone;

	private String logTime;

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
}
