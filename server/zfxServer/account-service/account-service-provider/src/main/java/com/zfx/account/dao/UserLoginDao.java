package com.moneylocker.account.dao;

import com.moneylocker.account.bean.LoginInfo;

public interface UserLoginDao {

	public LoginInfo getUserLoginByLoginId(String loginId);
	
	public String saveUserLoginInfo(String userId );

	public int delete(String userId, String loginId);

	public String getLoginIdByUserId(String userId);
}
