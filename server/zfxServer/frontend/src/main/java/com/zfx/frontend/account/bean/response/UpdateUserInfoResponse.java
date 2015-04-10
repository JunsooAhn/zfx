package com.moneylocker.frontend.account.bean.response;

import com.moneylocker.frontend.common.bean.Response;

public class UpdateUserInfoResponse extends Response {

	public static final int SUCCESS_FIRST_TIME = 210;

	public static final int NICK_NAME_EXIST = -100;

	public static final int VERIFY_ERROR = -200;

	public static final int PASSWORD_WRONG = -500;
}
