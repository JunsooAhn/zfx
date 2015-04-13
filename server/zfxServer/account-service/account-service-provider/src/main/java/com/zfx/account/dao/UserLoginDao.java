package com.zfx.account.dao;

import com.zfx.account.bean.LoginInfo;

public interface UserLoginDao {

	public LoginInfo getUserLoginByLoginId(String loginId);
	
	public String saveUserLoginInfo(String userId );

	public int delete(String userId, String loginId);

	public String getLoginIdByUserId(String userId);
}
