package com.zfx.consumer.dao;

import com.zfx.account.bean.UserLoginLog;

public interface UserCheatDao {

	public boolean addUserLoginToCheatList(UserLoginLog loginResult);

	public boolean removeUserFromCheatList(String userId);

}
