package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class UpdatePasswordRequest extends Request {

	private String oldPassword;

	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (StringUtils.isEmpty(newPassword)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.NEW_PASSWORD_EMPTY);
		}

		if (StringUtils.isEmpty(oldPassword)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.OLD_PASSWORD_EMPTY);
		}
		return null;
	}
}
