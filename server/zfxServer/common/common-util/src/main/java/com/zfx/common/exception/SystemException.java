package com.moneylocker.common.exception;

public class SystemException extends Exception {

	private static final long serialVersionUID = 3027778770881087920L;

	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public SystemException() {

	}

	public SystemException(String msgId) {
		this.msgId = msgId;
	}
}
