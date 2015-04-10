package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class RetrievePasswordRequest extends Request {

	private String imei;

	private String phone;

	private String password;

	private String verifyCode;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (!StringUtils.isValidImei(imei)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.RETRIEVE_IMEI_INVALID);
		}
		
		if (!StringUtils.isValidPhone(phone)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.RETRIEVE_PHONE_INVALID);
		}

		if (StringUtils.isEmpty(password)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.RETRIEVE_PASSWORD_EMPTY);
		}
		
		if (StringUtils.isEmpty(verifyCode)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.RETRIEVE_VERIFY_CODE_EMPTY);
		}
		return null;
	}
}
