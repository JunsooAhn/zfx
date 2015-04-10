package com.moneylocker.consumer.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.moneylocker.account.bean.UserLoginLog;
import com.moneylocker.common.dao.impl.BaseServiceDaoImpl;
import com.moneylocker.common.util.MapUtil;
import com.moneylocker.consumer.dao.UserCheatDao;

@Repository
public class UserCheatDaoImpl extends BaseServiceDaoImpl implements UserCheatDao {

	private static String NAME_SPACE_INFO = "com.moneylocker.account.bean.UserLoginResult";

	@Override
	public boolean addUserLoginToCheatList(UserLoginLog loginResult) {
		Date now = new Date();

		int insert = getSqlSession().insert(
				NAME_SPACE_INFO + ".insertUserIntoCheatLog",
				MapUtil.build("userId", loginResult.getUserId(), "imei", loginResult.getImei(), "model",
							loginResult.getModel(), "manufacturer", loginResult.getManufacturer(), "osVersion",
							loginResult.getOsVersion(), "appVersion", loginResult.getAppVersion(), "androidId",
							loginResult.getAndroidId(), "macAddress", loginResult.getMacAddress(), "procDate",
							now.getTime()));

			if (insert > 0) {
				return true;
			}


		return false;
	}

	@Override
	public boolean removeUserFromCheatList(String userId) {
		int delete = getSqlSession().delete(NAME_SPACE_INFO + ".deleteUserFromCheatLog",
				MapUtil.build("userId", userId));
		if (delete > 0) {
			return true;
		}
		return false;
	}

}
