package com.zfx.account.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Administrator
 * @JSONField(serialize = false)
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 6720546254540185313L;

	private String userId;

	private String nickName;

	private int userAge;

	public UserInfo() {

	}

	public UserInfo(String userId, String nickName, int userAge) {
		this.userId = userId;
		this.nickName = nickName;
		this.userAge = userAge;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

}
