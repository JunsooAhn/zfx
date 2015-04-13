package com.zfx.account.service.impl;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zfx.account.bean.LoginResult;
import com.zfx.account.constant.LoginResultState;
import com.zfx.account.constant.RebindPhoneState;
import com.zfx.account.constant.UpdateInfoState;
import com.zfx.account.service.AccountService;
import com.zfx.account.service.LoginService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
public class AccountServiceImplTest {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LoginService loginService;

	static String randomUUID = UUID.randomUUID().toString();	
	
	@Test
	@Transactional
	public void updateUserNickTest() {
		int updateUserNick = accountService.updateUserNick(randomUUID, "111");
		Assert.assertEquals(UpdateInfoState.NICK_NAME_EXIST, updateUserNick);
	}

	
	@Test
	@Transactional
	public void bindPhoneTest() {
		LoginResult loginResult = loginService.loginByImei("111not-exist", "111", "111", "111", 1);
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.REGISTER_SUCCESS);
		int result = accountService
				.bindPhone(loginResult.getUserInfo().getUserId(), "15921564973", "123456", (short) 1);
		Assert.assertEquals(result, UpdateInfoState.SUCCESS_FIRST_TIME);
	}

	@Test
	@Transactional
	public void rebindPhoneTest() {
		LoginResult loginResult = loginService.loginByImei("111not-exist", "111", "111", "111", 1);
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.REGISTER_SUCCESS);
		int result = accountService
				.bindPhone(loginResult.getUserInfo().getUserId(), "15921564973", "123456", (short) 1);
		result = accountService.rebindPhone(loginResult.getUserInfo().getUserId(), "15921564974", "15921564973",
				"123456", (short) 2, 30);
		Assert.assertEquals(result, RebindPhoneState.SUCCESS);
	}
}
