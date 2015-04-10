package com.moneylocker.account.service.impl;

import org.springframework.stereotype.Service;

import com.moneylocker.account.bean.LoginResult;
import com.moneylocker.account.service.LoginService;

@Service
public class MockLoginServiceImpl implements LoginService {

	@Override
	public int getLoginState(String loginId, String userId) {
		return 0;
	}

	@Override
	public LoginResult loginByPhone(String phone, String password, String imei, String model, String manufacturer,
			String osVersion, int appVersion, String androidId, String macAddress) {
		return null;
	}

	@Override
	public LoginResult loginByImei(String imei, String model, String manufacturer, String osVersion, int appVersion) {
		return null;
	}

	@Override
	public LoginResult registerNewUser(String userImei, String userPhone, String userPassword, Short verType,
			String model, String manufacturer, String osVersion, int appVersion, String androidId, String macAddress) {
		return null;
	}

	@Override
	public int logout(String userId, String loginId) {
		return 0;
	}

	@Override
	public LoginResult loginByDevice(String imei, String androidId, String macAddress) {
		return null;
	}
}
