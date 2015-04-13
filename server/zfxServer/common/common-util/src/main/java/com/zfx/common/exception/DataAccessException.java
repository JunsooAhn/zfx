package com.zfx.common.exception;

public class DataAccessException extends org.springframework.dao.DataAccessException {

	private static final long serialVersionUID = -2635934344816451390L;

	public DataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
