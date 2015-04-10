package com.moneylocker.consumer.dao;

import com.moneylocker.account.bean.UserLoginLog;

public interface UserCheatDao {

	public boolean addUserLoginToCheatList(UserLoginLog loginResult);

	public boolean removeUserFromCheatList(String userId);

}
