package com.zfx.account.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.zfx.account.bean.LoginInfo;
import com.zfx.account.dao.UserLoginDao;
import com.zfx.common.dao.impl.BaseServiceDaoImpl;
import com.zfx.common.util.MapUtil;
import com.zfx.common.util.UUIDGenerator;

@Repository
public class UserLoginDaoImpl extends BaseServiceDaoImpl implements UserLoginDao {

	private final static String NAMESPACE_USERLOGIN = "com.zfx.account.bean.UserLogin";

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
