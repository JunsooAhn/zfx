package com.moneylocker.frontend.common.bean;

import java.io.Serializable;

public class VerifyInfo implements Serializable {

	private static final long serialVersionUID = 2992497718582926873L;

	private String verifyCode;

	private int verifyType;

	public VerifyInfo(String verifyCode, int verifyType) {
		this.verifyCode = verifyCode;
		this.verifyType = verifyType;
	}

	public VerifyInfo() {
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public int getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(int verifyType) {
		this.verifyType = verifyType;
	}
}
