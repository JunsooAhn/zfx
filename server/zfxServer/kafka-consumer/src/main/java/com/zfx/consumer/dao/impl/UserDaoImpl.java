package com.zfx.consumer.dao.impl;

import org.springframework.stereotype.Repository;

import com.zfx.account.bean.UserInfo;
import com.zfx.common.dao.impl.BaseServiceDaoImpl;
import com.zfx.consumer.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseServiceDaoImpl implements UserDao {

	private final static String NAMESPACE_USERINFO = "com.zfx.account.bean.UserInfo";

	@Override
	public UserInfo getUserInfoById(String userId) {

		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".findByUserId", userId);

	}

}
