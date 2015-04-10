package com.moneylocker.account.bean;

import java.io.Serializable;

public class LoginResult implements Serializable {

	private static final long serialVersionUID = -8317143678454292940L;

	private int loginResultState;

	private String loginId;

	private UserInfo userInfo;

	public int getLoginResultState() {
		return loginResultState;
	}

	public void setLoginResultState(int loginResultState) {
		this.loginResultState = loginResultState;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}


}
