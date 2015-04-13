package com.zfx.frontend.account.bean.request;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

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
