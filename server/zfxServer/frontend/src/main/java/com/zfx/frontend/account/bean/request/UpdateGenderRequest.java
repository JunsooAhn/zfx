package com.zfx.frontend.account.bean.request;

import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class UpdateGenderRequest extends Request {

	private short gender;

	public short getGender() {
		return gender;
	}

	public void setGender(short gender) {
		this.gender = gender;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}
		if (gender < 1 || gender > 2) {
			return Response.build(Response.PARAM_INVALID, MsgContants.GENDER_INVALID);
		}
		return null;
	}
}
