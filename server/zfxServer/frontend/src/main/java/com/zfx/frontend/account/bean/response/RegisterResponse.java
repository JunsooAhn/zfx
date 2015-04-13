package com.zfx.frontend.account.bean.response;

import com.zfx.account.bean.UserInfo;
import com.zfx.frontend.common.bean.Response;

public class RegisterResponse extends Response {

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

	public static final int PHONE_EXISTS = -100;

	public static final int VERIFY_ERROR = -200;
}
