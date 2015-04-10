package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class RegisterRequest extends Request {

	private String imei;

	private String phone;

	private String password;

	private String verifyCode;
	
	private String model;
	
	private String manufacturer;
	
	private String osVersion;
	
	private int appVersion;

	private String androidId;

	private String macAddress;

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
		if (!StringUtils.isValidImei(imei)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_IMEI_INVALID);
		}

		if (StringUtils.isEmpty(password)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_PASSWORD_EMPTY);
		}

		if (!StringUtils.isValidPhone(phone)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_PHONE_INVALID);
		}

		if (StringUtils.isEmpty(verifyCode)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_VERIFY_CODE_EMPTY);
		}
		if (StringUtils.isEmpty(this.androidId)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_ANDROID_ID_INVALID);
		}
		if (StringUtils.isEmpty(this.macAddress)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.REGISTER_MAC_ADDRESS_INVALID);
		}
		return null;
	}


}
