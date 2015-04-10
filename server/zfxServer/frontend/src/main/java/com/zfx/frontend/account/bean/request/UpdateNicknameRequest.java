package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class UpdateNicknameRequest extends Request {

	private String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (StringUtils.isEmpty(nickName)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.NICK_NAME_EMPTY);
		}

		if (nickName.length() > 8) {
			return Response.build(Response.PARAM_INVALID, MsgContants.NICK_NAME_TOO_LONG);
		}
		return null;
	}
}
