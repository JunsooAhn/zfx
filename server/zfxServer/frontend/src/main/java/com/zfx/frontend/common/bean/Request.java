package com.moneylocker.frontend.common.bean;

import org.springframework.util.StringUtils;

import com.moneylocker.frontend.common.constant.MsgContants;

public class Request {

	private String loginId;

	private String userId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Response validate() {
		if (StringUtils.isEmpty(loginId) || StringUtils.isEmpty(userId) || loginId.length() != 32
				|| userId.length() != 32) {
			Response response = Response.build(Response.NOT_LOGIN, MsgContants.NOT_LOGIN);
			return response;
		}
		return null;
	}

	public String getDuplicateKey() {
		return loginId;
	}
}
