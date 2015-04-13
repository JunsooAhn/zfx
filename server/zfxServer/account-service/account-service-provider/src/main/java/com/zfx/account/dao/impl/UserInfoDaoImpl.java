package com.zfx.account.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zfx.account.bean.UserInfo;
import com.zfx.account.dao.UserInfoDao;
import com.zfx.common.dao.impl.BaseServiceDaoImpl;
import com.zfx.common.util.EncryptUtil;
import com.zfx.common.util.MapUtil;

@Repository
public class UserInfoDaoImpl extends BaseServiceDaoImpl implements UserInfoDao {

	private final static String NAMESPACE_USERINFO = "com.zfx.account.bean.UserInfo";

	@Override
	public boolean saveUserInfo(UserInfo u) {
		int insert = getSqlSession().insert(
				NAMESPACE_USERINFO + ".saveUserInfo", u);
		if (insert > 0) {
			return true;
		}
		return false;
	}

}
