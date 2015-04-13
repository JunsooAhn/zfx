package com.zfx.frontend.account.bean.response;

import com.zfx.account.bean.UserInfo;
import com.zfx.frontend.common.bean.Response;

public class PhoneLoginResponse extends Response {

	private String loginId;

	private UserInfo userInfo;

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

	public static final int ACOUNT_NOT_EXIST = -100;

	public static final int PASSWORD_INCORRECT = -200;
	
	public static final int ACCOUNT_FREEZE = -300;

	public static final int ACCOUNT_CHANGE_FREQUENTLY = -400;

	public static final int IMEI_DUPLICATE = 220;

	public static final int ACCOUNT_CREATE_SUCCESS = 210;
}
