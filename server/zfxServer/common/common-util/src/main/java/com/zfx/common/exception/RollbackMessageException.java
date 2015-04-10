package com.moneylocker.common.exception;

public class RollbackMessageException extends RuntimeException {

	private static final long serialVersionUID = 8456605707399819397L;
	
	private int type;

	public RollbackMessageException(int type) {
		super();
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
