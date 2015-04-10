package com.moneylocker.account.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 6720546254540185313L;

	private String userId;
	
	private String nickName;
	
	private String recommendedCode;

	private String userOrigin;

	private Short gender;

	private String birthday;
	
	private String phone;
	
	private Short viewHspInfoFlg;
	
	private String baiduId;
	
	private String baiduChannelId;

	private Short state;
	
	private Short userCheatState;

	private String updatePhoneTime;

	@JSONField(serialize = false)
	private Short isWhite; // 0 不是 1 是

	@JSONField(serialize = false)
	private String userRegtime;

	@JSONField(serialize = false)
	private String userPassword;

	public UserInfo() {
	}
	
	public UserInfo(String userId, String recommendedCode) {
		this.userId = userId;
		this.recommendedCode = recommendedCode;
	}
	
	public UserInfo(String userId, String recommendedCode,String phone) {
		this(userId,recommendedCode);
		this.phone = phone;
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

	public String getRecommendedCode() {
		return recommendedCode;
	}

	public void setRecommendedCode(String recommendedCode) {
		this.recommendedCode = recommendedCode;
	}

	public String getUserOrigin() {
		return userOrigin;
	}

	public void setUserOrigin(String userOrigin) {
		this.userOrigin = userOrigin;
	}

	public Short getGender() {
		return gender;
	}

	public void setGender(Short gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Short getViewHspInfoFlg() {
		return viewHspInfoFlg;
	}

	public void setViewHspInfoFlg(Short viewHspInfoFlg) {
		this.viewHspInfoFlg = viewHspInfoFlg;
	}

	public String getBaiduId() {
		return baiduId;
	}

	public void setBaiduId(String baiduId) {
		this.baiduId = baiduId;
	}

	public String getBaiduChannelId() {
		return baiduChannelId;
	}

	public void setBaiduChannelId(String baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getUserCheatState() {
		return userCheatState;
	}

	public void setUserCheatState(Short userCheatState) {
		this.userCheatState = userCheatState;
	}

	public Short getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Short isWhite) {
		this.isWhite = isWhite;
	}

	public String getUserRegtime() {
		return userRegtime;
	}

	public void setUserRegtime(String userRegtime) {
		this.userRegtime = userRegtime;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUpdatePhoneTime() {
		return updatePhoneTime;
	}

	public void setUpdatePhoneTime(String updatePhoneTime) {
		this.updatePhoneTime = updatePhoneTime;
	}
}
