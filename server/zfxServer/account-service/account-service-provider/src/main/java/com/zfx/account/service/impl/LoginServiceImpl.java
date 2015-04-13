package com.zfx.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zfx.account.bean.LoginResult;
import com.zfx.account.bean.UserInfo;
import com.zfx.account.constant.LoginResultState;
import com.zfx.account.constant.LoginState;
import com.zfx.account.constant.MemcacheKeyConstants;
import com.zfx.account.dao.UserInfoDao;
import com.zfx.account.dao.UserLoginDao;
import com.zfx.account.service.LoginService;
import com.zfx.common.util.KafkaProducer;
import com.zfx.common.util.UUIDGenerator;

@Service(value = "loginService")
@Transactional(value = "serviceTxManager")
public class LoginServiceImpl implements LoginService {


	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private UserInfoDao userInfoDao;


	@Override
	@Transactional(readOnly = true)
	public int getLoginState(String loginId, String userId) {
		String memcacheKey = MemcacheKeyConstants.LOGIN_STATE_PREFIX + loginId;
		return LoginState.NOT_LOGIN;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult loginByPhone(String phone, String password, String imei, String model, String manufacturer,
			String osVersion, int appVersion, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();


		return loginResult;
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Deprecated
	public LoginResult loginByImei(String imei, String model, String manufacturer, String osVersion, int appVersion) {
		LoginResult loginResult = new LoginResult();

		loginResult.setLoginResultState(LoginResultState.SUCCESS);
		return loginResult;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult loginByDevice(String imei, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();

		loginResult.setLoginResultState(LoginResultState.SUCCESS);
		return loginResult;
	}

	private String addLoginInfo(String userId) {
		String loginId = userLoginDao.saveUserLoginInfo(userId);
		String memcacheKey = MemcacheKeyConstants.LOGIN_STATE_PREFIX + loginId;
		return loginId;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult registerNewUser(String userImei, String userPhone, String userPassword, Short verType,
			String model, String manufacturer, String osVersion, int appVersion, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();
		userInfoDao.saveUserInfo(new UserInfo(UUIDGenerator.generate(),"fff",22));
		loginResult.setLoginResultState(LoginResultState.SUCCESS);

		return loginResult;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int logout(String userId, String loginId) {
		return LoginResultState.SUCCESS;
	}


}
