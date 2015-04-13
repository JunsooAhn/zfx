package com.zfx.frontend.account.bean.request;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class PhoneLoginRequest extends Request {

	private String phone;

	private String password;

	private String imei;

	private String model;

	private String manufacturer;

	private String osVersion;

	private int appVersion;

	private String androidId;

	private String macAddress;

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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public int getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
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

	@Override
	public Response validate() {
		if (!StringUtils.isValidPhone(phone)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.LOGIN_PHONE_INVALID);
		}

		if (StringUtils.isEmpty(password)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.LOGIN_PASSWORD_EMPTY);
		}

		if (!StringUtils.isValidImei(imei)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.LOGIN_IMEI_INVALID);
		}

		return null;
	}

	@Override
	public String getDuplicateKey() {
		return phone;
	}
}
