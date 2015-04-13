package com.zfx.account.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zfx.account.bean.UserInfo;
import com.zfx.common.util.UUIDGenerator;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
@Service
public class UserInfoDaoTest {
	
	@Autowired
	public UserInfoDao userInfoDao;
	
	@Test
	public void testSaveUserInfoMap() {
		userInfoDao.saveUserInfo(new UserInfo(UUIDGenerator.generate(),"fff",22));
	}
		
}
