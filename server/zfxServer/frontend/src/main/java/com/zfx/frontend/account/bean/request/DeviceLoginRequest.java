package com.zfx.frontend.account.bean.request;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class DeviceLoginRequest extends Request {

	private String imei;

	private String androidId;

	private String macAddress;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Override
	public String getDuplicateKey() {
		return imei + androidId + macAddress;
	}

	@Override
	public Response validate() {
		if (!StringUtils.isValidImei(imei) && !org.springframework.util.StringUtils.isEmpty(androidId)
				&& !org.springframework.util.StringUtils.isEmpty(macAddress)) {

			return Response.build(Response.PARAM_INVALID, MsgContants.LOGIN_IMEI_INVALID);
		}
		return null;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
}
