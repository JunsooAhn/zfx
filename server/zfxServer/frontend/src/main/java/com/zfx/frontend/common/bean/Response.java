package com.moneylocker.frontend.common.bean;

public class Response {

	private int statusCode;

	private String msg;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static Response build(int statusCode, String msg) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		response.setMsg(msg);
		return response;
	}

	public static Response build(int statusCode) {
		Response response = new Response();
		response.setStatusCode(statusCode);
		return response;
	}

	public static final int PARAM_INVALID = -10;

	public static final int NOT_LOGIN = -20;

	public static final int DUPLICATE_REQUEST = -30;

	public static final int PARAM_PARSE_ERROR = -40;

	public static final int SERVER_INTERNAL_ERROR = -60;

	public static final int FAILED = -50;

	public static final int SUCCESS = 200;
}
