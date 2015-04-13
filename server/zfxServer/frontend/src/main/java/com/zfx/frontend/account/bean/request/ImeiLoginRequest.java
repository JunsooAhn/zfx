package com.zfx.frontend.account.bean.request;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class ImeiLoginRequest extends Request {

	private String imei;

	private String model;

	private String manufacturer;

	private String osVersion;

	private int appVersion;

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

	@Override
	public String getDuplicateKey() {
		return imei;
	}

	@Override
	public Response validate() {
		if (!StringUtils.isValidImei(imei)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.LOGIN_IMEI_INVALID);
		}
		return null;
	}
}
