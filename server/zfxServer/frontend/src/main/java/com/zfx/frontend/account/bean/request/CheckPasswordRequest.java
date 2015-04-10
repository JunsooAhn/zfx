package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class CheckPasswordRequest extends Request {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.PASSWORD_EMPTY);
		}
		return null;
	}
}
