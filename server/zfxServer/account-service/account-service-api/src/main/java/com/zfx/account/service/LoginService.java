package com.zfx.account.service;

import com.zfx.account.bean.LoginResult;

public interface LoginService {

	public int getLoginState(String loginId, String userId);

	public LoginResult loginByPhone(String phone, String password, String imei, String model, String manufacturer,
			String osVersion, int appVersion, String androidId, String macAddress);

	@Deprecated
	public LoginResult loginByImei(String imei, String model, String manufacturer, String osVersion, int appVersion);

	public LoginResult registerNewUser(String userImei, String userPhone, String userPassword, Short verType,
			String model, String manufacturer, String osVersion, int appVersion, String androidId, String macAddress);

	public int logout(String userId, String loginId);

	public LoginResult loginByDevice(String imei, String androidId, String macAddress);
}
