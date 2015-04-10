package com.moneylocker.account.service.impl;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.bean.LoginResult;
import com.moneylocker.account.constant.LoginResultState;
import com.moneylocker.account.constant.LoginState;
import com.moneylocker.account.service.LoginService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
public class LoginServiceImplTest {

	@Autowired
	private LoginService loginService;

	static String randomUUID = UUID.randomUUID().toString();	
	
	@Test
	@Transactional
	public void getLoginStateTest(){
		int state = loginService.getLoginState(randomUUID, "111");
		Assert.assertEquals(LoginState.NOT_LOGIN, state);
	}
	
	@Test
	@Transactional
	public void getLoginStateLogin() {
		LoginResult loginResult = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa",
				"bbb", 1, "androidId", "mac Id");
		int state = loginService.getLoginState(loginResult.getLoginId(), loginResult.getUserInfo().getUserId());
		Assert.assertEquals(LoginState.LOGIN, state);
	}

	@Test
	@Transactional
	public void loginByPhone(){
		LoginResult loginResult = loginService.loginByPhone("111", "111", "111", "222", "111", "111", 1, "androidId", "macAddress");
		Assert.assertEquals(LoginResultState.PASSWORD_INVALID, loginResult.getLoginResultState());
	}
	
	@Test
	@Transactional
	public void loginByPhoneSuccess() {
		LoginResult loginResult = new LoginResult();
		loginResult = loginService.registerNewUser("ss", "123456789", "12345", (short) 1, "aaa", "aaa", "bbb", 1,
				"androidId", "mac Id");
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.SUCCESS);
		loginResult = loginService.loginByPhone("123456789", "12345", "111", "222", "111", "111", 1, "androidId", "macAddress");
		Assert.assertEquals(LoginResultState.SUCCESS, loginResult.getLoginResultState());
	}

	@Test
	@Transactional
	public void loginByPhoneCheat() {
		LoginResult loginResult = new LoginResult();
		loginResult = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa", "aaa", 1,
				"androidId", "mac Id");
		loginResult = loginService.registerNewUser("ss2", "123456790", "12345", (short) 1, "bbb", "bbb", "bbb", 1,
				"androidId", "mac Id");
		loginResult = loginService.registerNewUser("ss3", "123456791", "12345", (short) 1, "ccc", "ccc", "ccc", 1,
				"androidId", "mac Id");
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.SUCCESS);
		loginResult = loginService.loginByPhone("123456789", "12345", "ss1", "222", "111", "111", 1, "androidId", "macAddress");
		loginResult = loginService.loginByPhone("123456790", "12345", "ss1", "222", "111", "111", 1, "androidId", "macAddress");
		loginResult = loginService.loginByPhone("123456791", "12345", "ss1", "222", "111", "111", 1, "androidId", "macAddress");
		Assert.assertEquals(LoginResultState.ACCOUNT_CHANGE_FREQUENTLY, loginResult.getLoginResultState());
	}

	@Test
	@Transactional
	public void registerNewUser(){
		LoginResult loginResult = new LoginResult();
		loginResult = loginService.registerNewUser("ss", "123456789", "12345", (short) 1, "aaa", "aaa", "aaa", 1,
				"androidId", "mac Id");
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.SUCCESS);
		Assert.assertEquals(loginResult.getUserInfo().getPhone(), "123456789");
		Assert.assertNotNull(loginResult.getLoginId());
	}
	
	@Test
	@Transactional
	@Deprecated
	public void loginByImei(){
		LoginResult loginResult = new LoginResult();
		loginResult = loginService.loginByImei("111not-exist", "111", "111", "111", 1);
		Assert.assertEquals(loginResult.getLoginResultState(), LoginResultState.REGISTER_SUCCESS);
		int state = loginService.getLoginState(loginResult.getLoginId(), loginResult.getUserInfo().getUserId());
		Assert.assertEquals(LoginState.LOGIN, state);
	}
}
