package com.moneylocker.common.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7385387460157290511L;

	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public BusinessException() {

	}

	public BusinessException(String msgId) {
		this.msgId = msgId;
	}
}
