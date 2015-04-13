package com.zfx.account.bean;

import java.io.Serializable;

public class AddReferenceResult implements Serializable {

	private static final long serialVersionUID = 1357158433757766768L;

	public int state;

	public String userId;

	public AddReferenceResult() {
	}

	public AddReferenceResult(int state, String userId) {
		this.state = state;
		this.userId = userId;
	}

	public AddReferenceResult(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
