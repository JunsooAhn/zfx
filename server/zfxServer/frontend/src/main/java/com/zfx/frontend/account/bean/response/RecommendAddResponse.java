package com.moneylocker.frontend.account.bean.response;

import com.moneylocker.frontend.common.bean.Response;

public class RecommendAddResponse extends Response {

	public static final int USER_NOT_EXIST = -200;

	public static final int ORIGIN_ALREAY_FILLED = -300;

	public static final int REFERENCE_CODE_NOT_EXIST = -400;

	public static final int CAN_NOT_FILL_SELF = -500;
}
