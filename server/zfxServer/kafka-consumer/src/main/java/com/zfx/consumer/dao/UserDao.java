package com.moneylocker.consumer.dao;

import com.moneylocker.account.bean.UserInfo;

public interface UserDao {
	public UserInfo getUserInfoById(String userId);
}
