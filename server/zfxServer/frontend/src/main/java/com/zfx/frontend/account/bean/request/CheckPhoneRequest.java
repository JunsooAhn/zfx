package com.zfx.frontend.account.bean.request;

import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class CheckPhoneRequest extends Request{
	
	private String phone;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
