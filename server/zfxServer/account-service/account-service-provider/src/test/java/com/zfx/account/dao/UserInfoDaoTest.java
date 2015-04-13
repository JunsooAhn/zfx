package com.zfx.account.dao;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zfx.account.bean.UserInfo;
import com.zfx.account.constant.UserPartInfoType;
import com.zfx.common.util.EncryptUtil;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
@Service
public class UserInfoDaoTest {
	
	@Autowired
	public UserInfoDao userInfoDao;
	
	@Test
	@Transactional
	public void testSaveUserInfoMap() {
		String userRegtime = String.valueOf(System.currentTimeMillis());
		String recommendedCode = "123";
		userInfoDao.saveUserInfo("00001", "1111111111", "123123", "testImei", recommendedCode, (short) 0, "111", "111",
				"111", 1, "android Id", "122.323.23.3");
		
		UserInfo findByUserPhone = userInfoDao.findByUserPhone("1111111111");
		Assert.assertEquals("1111111111", findByUserPhone.getPhone());
		Assert.assertEquals("00001", findByUserPhone.getUserId());
		Assert.assertEquals("123", findByUserPhone.getRecommendedCode());
		Assert.assertEquals(userRegtime, findByUserPhone.getUserRegtime());
		
		List<UserInfo> findRegUserImei = userInfoDao.findRegUserImei("testImei");
		Assert.assertEquals(1, findRegUserImei.size());
		UserInfo userInfo = findRegUserImei.get(0);
		Assert.assertEquals("1111111111", userInfo.getPhone());
		Assert.assertEquals("00001", userInfo.getUserId());
		Assert.assertEquals("123", userInfo.getRecommendedCode());
		
		boolean isExist = userInfoDao.isRecommendedCodeExist("123");
		Assert.assertEquals(true, isExist);
		
		boolean isExist2 = userInfoDao.isExistSingleProperty(5, "1111111111");
		Assert.assertEquals(true, isExist2);
		
		Map<String, Object> passwordInfo = userInfoDao.getPartInfoByUserId("00001", UserPartInfoType.PASSWORD);
		Assert.assertEquals(userRegtime, passwordInfo.get("regTime"));
	}
	
	@Test
	@Transactional
	public void updateUserInfos() {
		String userRegtime = String.valueOf(System.currentTimeMillis());
		userInfoDao.saveUserInfo("00002", "1111111111", EncryptUtil.sha("123" + userRegtime), "testImei", "456",
				(short) 0, "111", "111", "111", 1, "android Id", "mac Id");
		
		String updatePhoneTime = String.valueOf(System.currentTimeMillis());
		boolean updateResult = userInfoDao.rebindPhone("00002", "1111111112", "1111111111", updatePhoneTime, (short) 1);
		Assert.assertEquals(true, updateResult);	
		
		boolean updateBaiduResult = userInfoDao.updateBaiduId("00002", "5555", "1112", "3h");
		Assert.assertEquals(true, updateBaiduResult);	
	}
	
	@Test
	@Transactional
	public void updateUserInfoSinglePropertyWithCheck() {
		String userRegtime = String.valueOf(System.currentTimeMillis());
		userInfoDao.saveUserInfo("00003", "1111111111", EncryptUtil.sha("123" + userRegtime), "testImei", "456",
				(short) 0, "111", "111", "111", 1, "android id", "mac id");
		
		boolean updateResult = userInfoDao.updateUserInfoSinglePropertyWithCheck("00003", 1, "1979-05", null);
		Assert.assertEquals(true, updateResult);	
	}
	
	@Test
	@Transactional
	public void testFindByUserPhone() {
		System.out.println(userInfoDao);
		
	}
}
