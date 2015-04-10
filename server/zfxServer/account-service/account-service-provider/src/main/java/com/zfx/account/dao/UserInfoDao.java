package com.moneylocker.account.dao;

import java.util.List;
import java.util.Map;

import com.moneylocker.account.bean.UserInfo;

public interface UserInfoDao {

	public <T> T getUserInfoSingleProperty(String userId, int userInfoType);

	public boolean rebindPhone(String userId, String phone, String oldPhone, String updatePhoneTime, Short verType);

	public boolean bindPhone(String userId, String phone, String password, String updatePhoneTime, Short verType);

	public boolean updateBaiduId(String userId, String baiduId, String baiduChannelId, String baiduGroup);

	public List<UserInfo> findRegUserImei(String regUserImei);

	public List<UserInfo> findRegUserDevice(String imei, String androidId, String macAddress);
	
	public boolean isRecommendedCodeExist(String recommendedCode);

	@Deprecated
	public boolean saveUserInfo(String userId, String imei, String recommendedCode);

	public boolean saveUserInfoWithDevice(String userId, String imei, String androidId, String macAddress,
			String recommendedCode);

	public <T> boolean updateUserInfoSinglePropertyWithCheck(String userId, int userInfoType, T newData, T oldData);

	public <T> boolean updateUserInfoSingleProperty(String userId, int userInfoType, T newData);

	public <T> boolean isExistSingleProperty(int userInfoType, T checkData);

	public UserInfo findByUserPhone(String phone);

	public boolean saveUserInfo(String userId, String userPhone, String userPassword, String userImei,
			String recommendedCode, Short verType, String model, String manufacturer, String osVersion, int appVersion,
			String androidId, String macAddress);

	public Map<String, Object> getPartInfoByUserId(String userId, int type);

	public Map<String, Object> getPartInfoByRecommendCode(String code, int type);

	public Map<String, Object> getPartInfoByPhone(String phone, int type);

	public UserInfo getUserInfoById(String userId);

	
}
