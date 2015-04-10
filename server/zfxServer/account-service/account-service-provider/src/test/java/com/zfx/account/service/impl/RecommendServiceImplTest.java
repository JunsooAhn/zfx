package com.moneylocker.account.service.impl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.bean.AddReferenceResult;
import com.moneylocker.account.bean.LoginResult;
import com.moneylocker.account.bean.RecommendInfo;
import com.moneylocker.account.constant.AddReferencesState;
import com.moneylocker.account.service.LoginService;
import com.moneylocker.account.service.RecommendService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "spring-test.xml")
public class RecommendServiceImplTest {

	@Autowired
	private LoginService loginService;

	@Autowired
	private RecommendService recommendService;

	@Test
	@Transactional
	public void addReferenceSuccess() {
		LoginResult loginResult1 = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa",
				"aaa", 1, "androidId", "mac Id");
		LoginResult loginResult2 = loginService.registerNewUser("ss2", "123456790", "12345", (short) 1, "bbb", "bbb",
				"bbb", 1, "androidId", "mac Id");
		AddReferenceResult addReferenceResult = recommendService.addReference(loginResult1.getUserInfo().getUserId(),
				loginResult2.getUserInfo().getRecommendedCode(), 300);
		Assert.assertEquals(addReferenceResult.getState(), AddReferencesState.SUCCESS);
		Assert.assertEquals(addReferenceResult.getUserId(), loginResult2.getUserInfo().getUserId());
	}

	@Test
	@Transactional
	public void addReferenceUserNotExist() {
		AddReferenceResult addReferenceResult = recommendService.addReference("aaa-not-exist", "bbb", 300);
		Assert.assertEquals(addReferenceResult.getState(), AddReferencesState.USER_NOT_EXIST);
	}

	@Test
	@Transactional
	public void addReferenceOriginAlredyFilled() {
		LoginResult loginResult1 = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa",
				"aaa", 1, "androidId", "mac Id");
		LoginResult loginResult2 = loginService.registerNewUser("ss2", "123456790", "12345", (short) 1, "bbb", "bbb",
				"aaa", 1, "androidId", "mac Id");
		recommendService.addReference(loginResult1.getUserInfo().getUserId(), loginResult2.getUserInfo()
				.getRecommendedCode(), 300);
		AddReferenceResult addReferenceResult = recommendService.addReference(loginResult1.getUserInfo().getUserId(),
				loginResult2.getUserInfo().getRecommendedCode(), 300);
		Assert.assertEquals(addReferenceResult.getState(), AddReferencesState.ORIGIN_ALREAY_FILLED);
	}

	@Test
	@Transactional
	public void addReferenceReferenceCodeNotExist() {
		LoginResult loginResult1 = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa",
				"aaa", 1, "androidId", "mac Id");
		AddReferenceResult addReferenceResult = recommendService.addReference(loginResult1.getUserInfo().getUserId(),
				"not-exist", 300);
		Assert.assertEquals(addReferenceResult.getState(), AddReferencesState.REFERENCE_CODE_NOT_EXIST);
	}

	@Test
	@Transactional
	public void getReference() {
		LoginResult loginResult1 = loginService.registerNewUser("ss1", "123456789", "12345", (short) 1, "aaa", "aaa",
				"aaa", 1, "androidId", "mac Id");
		LoginResult loginResult2 = loginService.registerNewUser("ss2", "123456790", "12345", (short) 1, "bbb", "bbb",
				"bbb", 1, "androidId", "mac Id");
		recommendService.addReference(loginResult1.getUserInfo().getUserId(),
				loginResult2.getUserInfo().getRecommendedCode(), 300);
		int num = recommendService.getRecommendSummary(loginResult2.getUserInfo().getUserId());
		List<RecommendInfo> recommendInfoList = recommendService.getRecommendDetail(loginResult2.getUserInfo()
				.getUserId());
		Assert.assertEquals(1, num);
		Assert.assertEquals(1, recommendInfoList.size());
		Assert.assertEquals("123456789", recommendInfoList.get(0).getPhone());
	}
}
