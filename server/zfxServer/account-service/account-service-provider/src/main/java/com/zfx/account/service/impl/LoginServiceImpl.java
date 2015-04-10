package com.moneylocker.account.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.bean.ImeiPhoneLog;
import com.moneylocker.account.bean.LoginInfo;
import com.moneylocker.account.bean.LoginResult;
import com.moneylocker.account.bean.UserInfo;
import com.moneylocker.account.bean.UserLoginLog;
import com.moneylocker.account.constant.LoginResultState;
import com.moneylocker.account.constant.LoginState;
import com.moneylocker.account.constant.MemcacheKeyConstants;
import com.moneylocker.account.constant.UserInfoType;
import com.moneylocker.account.constant.UserState;
import com.moneylocker.account.dao.UserInfoDao;
import com.moneylocker.account.dao.UserLoginDao;
import com.moneylocker.account.service.LoginService;
import com.moneylocker.common.constant.CommonConstants;
import com.moneylocker.common.constant.KafkaMessagesConst;
import com.moneylocker.common.service.MemcachedService;
import com.moneylocker.common.util.DateUtil;
import com.moneylocker.common.util.EncryptUtil;
import com.moneylocker.common.util.KafkaProducer;
import com.moneylocker.common.util.RandomUtil;
import com.moneylocker.common.util.UUIDGenerator;
import com.moneylocker.credit.service.UserCreditService;

@Service(value = "loginService")
@Transactional(value = "serviceTxManager")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemcachedService memcachedService;

	@Autowired
	private UserLoginDao userLoginDao;

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	@Qualifier("kafkaProducerASync")
	private KafkaProducer producer;


	@Override
	@Transactional(readOnly = true)
	public int getLoginState(String loginId, String userId) {
		String memcacheKey = MemcacheKeyConstants.LOGIN_STATE_PREFIX + loginId;
		LoginInfo loginInfo = (LoginInfo) memcachedService.memcachedGet(memcacheKey);
		if (loginInfo == null) {
			// 可能由于memcache过期，所以再次从数据库获取一遍
			loginInfo = userLoginDao.getUserLoginByLoginId(loginId);
			if (loginInfo != null) {
				memcachedService.memcachedSet(memcacheKey, CommonConstants.HOURS_24, loginInfo);
			}
		}
		if (loginInfo != null && loginInfo.getUserId().equals(userId)) {
			return LoginState.LOGIN;
		}
		return LoginState.NOT_LOGIN;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult loginByPhone(String phone, String password, String imei, String model, String manufacturer,
			String osVersion, int appVersion, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();

		UserInfo userInfo = userInfoDao.findByUserPhone(phone);

		if (userInfo == null
				|| !userInfo.getUserPassword().equals(EncryptUtil.sha(password + userInfo.getUserRegtime()))) {
			loginResult.setLoginResultState(LoginResultState.PASSWORD_INVALID);
			return loginResult;
		}

		if (userInfo.getState() == UserState.FREEZE) {
			loginResult.setLoginResultState(LoginResultState.ACCOUNT_FREEZE);
			return loginResult;
		}

		// 判断是不是之前已经登录了，登录了就清除memcache
		String oldLoginId = userLoginDao.getLoginIdByUserId(userInfo.getUserId());
		if (oldLoginId != null) {
			clearLoginCache(oldLoginId);
		}

		// 登录
		String loginId = addLoginInfo(userInfo.getUserId());

		// for log thing
		UserLoginLog loginLog = new UserLoginLog();
		loginLog.setUserId(userInfo.getUserId());
		loginLog.setDate(new Date());
		loginLog.setLoginId(loginId);
		loginLog.setImei(imei);
		loginLog.setModel(model);
		loginLog.setManufacturer(imei);
		loginLog.setOsVersion(osVersion);
		loginLog.setAppVersion(appVersion);
		loginLog.setAndroidId(androidId);
		loginLog.setMacAddress(macAddress);

		loginResult.setLoginId(loginId);
		loginResult.setUserInfo(userInfo);
		loginResult.setLoginResultState(LoginResultState.SUCCESS);

		producer.send("post_login_process", null, loginLog);
		producer.send("ml_login_log", null, loginLog);

		return loginResult;
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Deprecated
	public LoginResult loginByImei(String imei, String model, String manufacturer, String osVersion, int appVersion) {
		LoginResult loginResult = new LoginResult();

		List<UserInfo> userInfoList = userInfoDao.findRegUserImei(imei);
		if (userInfoList != null && userInfoList.size() > 1) {
			loginResult.setLoginResultState(LoginResultState.IEMI_ALREADY_EXISTS);
			return loginResult;
		}

		if (userInfoList == null || userInfoList.size() == 0) {
			String userId = UUIDGenerator.generate();
			userCreditService.initCredit(userId);
			String recommendedCode = getRecommendedCode();
			userInfoDao.saveUserInfo(userId, imei, recommendedCode);
			String loginId = userLoginDao.saveUserLoginInfo(userId);
			loginResult.setLoginId(loginId);
			loginResult.setUserInfo(new UserInfo(userId, recommendedCode));
			loginResult.setLoginResultState(LoginResultState.REGISTER_SUCCESS);
			return loginResult;
		}

		UserInfo userInfo = userInfoList.get(0);
		String loginId = addLoginInfo(userInfo.getUserId());
		loginResult.setLoginId(loginId);
		loginResult.setUserInfo(userInfo);
		loginResult.setLoginResultState(LoginResultState.SUCCESS);
		return loginResult;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult loginByDevice(String imei, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();

		List<UserInfo> userInfoList = userInfoDao.findRegUserDevice(imei, androidId, macAddress);
		if (userInfoList != null && userInfoList.size() > 1) {
			loginResult.setLoginResultState(LoginResultState.DEVICE_ALREADY_EXISTS);
			return loginResult;
		}

		if (userInfoList == null || userInfoList.size() == 0) {
			String userId = UUIDGenerator.generate();
			userCreditService.initCredit(userId);
			String recommendedCode = getRecommendedCode();
			userInfoDao.saveUserInfoWithDevice(userId, imei, androidId, macAddress, recommendedCode);
			String loginId = userLoginDao.saveUserLoginInfo(userId);
			loginResult.setLoginId(loginId);
			loginResult.setUserInfo(new UserInfo(userId, recommendedCode));
			loginResult.setLoginResultState(LoginResultState.REGISTER_SUCCESS);
			return loginResult;
		}

		UserInfo userInfo = userInfoList.get(0);
		String loginId = addLoginInfo(userInfo.getUserId());
		loginResult.setLoginId(loginId);
		loginResult.setUserInfo(userInfo);
		loginResult.setLoginResultState(LoginResultState.SUCCESS);
		return loginResult;
	}

	private String addLoginInfo(String userId) {
		String loginId = userLoginDao.saveUserLoginInfo(userId);
		String memcacheKey = MemcacheKeyConstants.LOGIN_STATE_PREFIX + loginId;
		memcachedService.memcachedSet(memcacheKey, CommonConstants.HOURS_24, new LoginInfo(userId));
		return loginId;
	}

	private String getRecommendedCode() {
		while (true) {
			String userCode = RandomUtil.randomCharAndNum(9);
			if (!userInfoDao.isRecommendedCodeExist(userCode)) {
				return userCode;
			}
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public LoginResult registerNewUser(String userImei, String userPhone, String userPassword, Short verType,
			String model, String manufacturer, String osVersion, int appVersion, String androidId, String macAddress) {
		LoginResult loginResult = new LoginResult();

		boolean isPhoneExists = userInfoDao.isExistSingleProperty(UserInfoType.PHONE, userPhone);
		if (isPhoneExists) {
			loginResult.setLoginResultState(LoginResultState.PHONE_EXISTS);
			return loginResult;
		}

		String userId = UUIDGenerator.generate();
		userCreditService.initCredit(userId);

		String recommendedCode = getRecommendedCode();

		userInfoDao.saveUserInfo(userId, userPhone, userPassword, userImei, recommendedCode, verType, model,
				manufacturer, osVersion, appVersion, androidId, macAddress);
		int today = DateUtil.toDayInt(Calendar.getInstance());
		ImeiPhoneLog imeiPhoneLog = new ImeiPhoneLog(userImei, androidId, macAddress, userPhone, (short) 0, today);
		producer.send(KafkaMessagesConst.SAVE_IMEI_MESSAGE, null, imeiPhoneLog);

		loginResult.setLoginId(addLoginInfo(userId));
		loginResult.setUserInfo(new UserInfo(userId, recommendedCode, userPhone));
		loginResult.setLoginResultState(LoginResultState.SUCCESS);

		return loginResult;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int logout(String userId, String loginId) {
		if (userLoginDao.delete(userId, loginId) > 0) {
			clearLoginCache(loginId);
		}
		return LoginResultState.SUCCESS;
	}

	private void clearLoginCache(String loginId) {
		String memcacheKey = MemcacheKeyConstants.LOGIN_STATE_PREFIX + loginId;
		memcachedService.memcachedDelete(memcacheKey);
	}

}
