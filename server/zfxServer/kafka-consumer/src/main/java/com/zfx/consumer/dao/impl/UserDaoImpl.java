package com.moneylocker.consumer.dao.impl;

import org.springframework.stereotype.Repository;

import com.moneylocker.account.bean.UserInfo;
import com.moneylocker.common.dao.impl.BaseServiceDaoImpl;
import com.moneylocker.consumer.dao.UserDao;

@Repository
public class UserDaoImpl extends BaseServiceDaoImpl implements UserDao {

	private final static String NAMESPACE_USERINFO = "com.moneylocker.account.bean.UserInfo";

	@Override
	public UserInfo getUserInfoById(String userId) {

		return getSqlSession().selectOne(NAMESPACE_USERINFO + ".findByUserId", userId);

	}

}
