package com.zfx.frontend.common.bean;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.common.constant.MsgContants;

public class VerifyRequest extends Request {

	private String phone;

	private int verifyReason;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Response validate() {
		if (!StringUtils.isValidPhone(phone)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.VERIFY_PHONE_INVALID);
		}
		return null;
	}

	@Override
	public String getDuplicateKey() {
		return phone;
	}

	public int getVerifyReason() {
		return verifyReason;
	}

	public void setVerifyReason(int verifyReason) {
		this.verifyReason = verifyReason;
	}


}
