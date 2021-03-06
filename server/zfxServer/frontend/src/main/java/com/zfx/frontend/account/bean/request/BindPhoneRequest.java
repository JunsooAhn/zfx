package com.zfx.frontend.account.bean.request;

import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class BindPhoneRequest extends Request {

	private String phone;

	private String password;

	private String verifyCode;

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

		// TODO 验证参数
		return null;
	}
}
