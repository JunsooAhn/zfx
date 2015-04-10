package com.moneylocker.account.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.bean.LoginInfo;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
@Service
public class UserLoginDaoTest {
	
	@Autowired
	public UserLoginDao userLoginDao;
	
	@Test
	@Transactional
	public void testSaveUserInfoMap() {
		Map<String, Object> userInfoMap = new HashMap<String, Object>();
		Date loginTime = new Date();
		userInfoMap.put("userId", "00001");
		userInfoMap.put("loginTime", loginTime);
		userLoginDao.saveUserLoginInfo("00001");
		
		String idString = userLoginDao.saveUserLoginInfo("00001");		
		LoginInfo loginInfo = userLoginDao.getUserLoginByLoginId(idString);
		Assert.assertEquals("00001", loginInfo.getUserId());
	}
}
