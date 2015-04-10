package com.moneylocker.account.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.moneylocker.account.bean.LoginInfo;
import com.moneylocker.account.dao.UserLoginDao;
import com.moneylocker.common.dao.impl.BaseServiceDaoImpl;
import com.moneylocker.common.util.MapUtil;
import com.moneylocker.common.util.UUIDGenerator;

@Repository
public class UserLoginDaoImpl extends BaseServiceDaoImpl implements UserLoginDao {

	private final static String NAMESPACE_USERLOGIN = "com.moneylocker.account.bean.UserLogin";

	@Override
	public LoginInfo getUserLoginByLoginId(String loginId) {
		LoginInfo loginInfo = getSqlSession().selectOne(NAMESPACE_USERLOGIN + ".getUserLoginByLoginId", loginId);
		return loginInfo;
	}

	@Override
	public String saveUserLoginInfo(String userId) {
		String id = UUIDGenerator.generate();
		getSqlSession().insert(NAMESPACE_USERLOGIN + ".saveUserLoginInfo",
				MapUtil.build("id", id, "userId", userId, "loginTime", new Date()));
		return id;
	}

	@Override
	public int delete(String userId, String loginId) {
		return getSqlSession().delete(NAMESPACE_USERLOGIN + ".delete",
				MapUtil.build("userId", userId, "loginId", loginId));
	}

	@Override
	public String getLoginIdByUserId(String userId) {
		return getSqlSession().selectOne(NAMESPACE_USERLOGIN + ".getUserLoginIdByUserId",
				MapUtil.build("userId", userId));
	}
}
