package com.moneylocker.account.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moneylocker.account.bean.UserInfo;
import com.moneylocker.account.dao.UserInfoDao;
import com.moneylocker.common.dao.impl.BaseServiceDaoImpl;
import com.moneylocker.common.util.EncryptUtil;
import com.moneylocker.common.util.MapUtil;

@Repository
public class UserInfoDaoImpl extends BaseServiceDaoImpl implements UserInfoDao {

	private final static String NAMESPACE_USERINFO = "com.moneylocker.account.bean.UserInfo";

	@Override
	public boolean rebindPhone(String userId, String phone, String oldPhone, String updatePhoneTime, Short verType) {
		int isEdit = getSqlSession().update(
				NAMESPACE_USERINFO + ".rebindPhone",
				MapUtil.build("userId", userId, "phone", phone, "oldPhone", oldPhone, "updatePhoneTime",
						updatePhoneTime, "verType", verType));
		if (isEdit == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean bindPhone(String userId, String phone, String password, String updatePhoneTime, Short verType) {
		int isEdit = getSqlSession().update(NAMESPACE_USERINFO + ".bindPhone",
				MapUtil.build("userId", userId, "phone", phone, "password", password, "updatePhoneTime",
						updatePhoneTime, "verType", verType));
		if (isEdit == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean updateBaiduId(String userId, String baiduId, String baiduChannelId, String baiduGroup) {
		int isEdit = getSqlSession().update(
				NAMESPACE_USERINFO + ".updateBaiduInfo",
				MapUtil.build("userId", userId, "baiduId", baiduId, "baiduChannelId", baiduChannelId, "baiduGroup",
						baiduGroup));
		if (isEdit == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<UserInfo> findRegUserImei(String imei) {
		return getSqlSession().selectList(NAMESPACE_USERINFO + ".findRegUserImei", imei);
	}

	@Override
	public List<UserInfo> findRegUserDevice(String imei, String androidId, String macAddress) {
		return getSqlSession().selectList(NAMESPACE_USERINFO + ".findRegUserByDevice",
				MapUtil.build("imei", imei, "androidId", androidId, "macAddress", macAddress));
	}

	@Override
	public boolean isRecommendedCodeExist(String recommendedCode) {
		int count = getSqlSession().selectOne(NAMESPACE_USERINFO + ".isRecommendedCodeExist", recommendedCode);
		if (count == 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveUserInfo(String userId, String imei, String recommendedCode) {
		int insert = getSqlSession().insert(
				NAMESPACE_USERINFO + ".saveUserInfoWithoutPhone",
				MapUtil.build("userId", userId, "userRecommendedCode", recommendedCode, "imei", imei, "userRegtime",
						String.valueOf(System.currentTimeMillis())));
		if (insert > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveUserInfoWithDevice(String userId, String imei, String androidId, String macAddress,
			String recommendedCode) {
		int insert = getSqlSession()
				.insert(NAMESPACE_USERINFO + ".saveUserInfoWidthDevice",
						MapUtil.build("userId", userId, "userRecommendedCode", recommendedCode, "imei", imei,
								"androidId", androidId, "macAddress", macAddress, "userRegtime",
								String.valueOf(System.currentTimeMillis())));
		if (insert > 0) {
			return true;
		}
		return false;
	}


	@Override
	public boolean saveUserInfo(String userId, String phone, String password, String imei, String recommendedCode,
			Short verType, String model, String manufacturer, String osVersion, int appVersion, String androidId,
			String macAddress) {
		String userRegtime = String.valueOf(System.currentTimeMillis());
		int insert = getSqlSession().insert(
				NAMESPACE_USERINFO + ".saveUserInfoWithPhone",
				MapUtil.build("userId", userId, "userRecommendedCode", recommendedCode, "imei", imei, "userRegtime",
						userRegtime, "password", EncryptUtil.sha(password + userRegtime), "phone", phone, "verType",
						verType, "model", model, "manufacturer", manufacturer, "osVersion", osVersion, "appVersion",
						appVersion, "androidId", androidId, "macAddress", macAddress, "phoneUpdateTime",
						new Date().getTime()));
		if (insert > 0) {
			return true;
		}
		return false;
	}

	@Override
	public <T> T getUserInfoSingleProperty(String userId, int userInfoType) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".getUserInfoSingleProperty",
				MapUtil.build("userId", userId, "propertyType", userInfoType));
	}

	@Override
	public <T> boolean updateUserInfoSinglePropertyWithCheck(String userId, int userInfoType, T newData, T oldData) {
		int isEdit = getSqlSession().update(NAMESPACE_USERINFO + ".updateUserInfoSinglePropertyWithOldCheck",
				MapUtil.build("userId", userId, "propertyType", userInfoType, "newData", newData, "oldData", oldData));
		if (isEdit == 0) {
			return false;
		}
		return true;
	}

	@Override
	public <T> boolean updateUserInfoSingleProperty(String userId, int userInfoType, T newData) {
		int isEdit = getSqlSession().update(NAMESPACE_USERINFO + ".updateUserInfoSingleProperty",
				MapUtil.build("userId", userId, "propertyType", userInfoType, "newData", newData));
		if (isEdit == 0) {
			return false;
		}
		return true;
	}

	@Override
	public <T> boolean isExistSingleProperty(int userInfoType, T checkData) {
		Integer count = getSqlSession().selectOne(NAMESPACE_USERINFO + ".isExistSingleProperty",
				MapUtil.build("propertyType", userInfoType, "checkData", checkData));
		if (count == null) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getPartInfoByUserId(String userId, int type) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".getPartInfoByUserId",
				MapUtil.build("userId", userId, "type", type));
	}

	@Override
	public UserInfo findByUserPhone(String phone) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".findByUserPhone", phone);
	}

	@Override
	public Map<String, Object> getPartInfoByRecommendCode(String code, int type) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".getPartInfoByRecommendCode",
				MapUtil.build("code", code, "type", type));
	}

	@Override
	public Map<String, Object> getPartInfoByPhone(String phone, int type) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".getPartInfoByPhone",
				MapUtil.build("phone", phone, "type", type));
	}

	@Override
	public UserInfo getUserInfoById(String userId) {
		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".findByUserId", userId);
	}


}
