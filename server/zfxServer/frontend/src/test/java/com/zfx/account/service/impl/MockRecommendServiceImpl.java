package com.moneylocker.account.service.impl;

import java.util.List;

import com.moneylocker.account.bean.AddReferenceResult;
import com.moneylocker.account.bean.RecommendInfo;
import com.moneylocker.account.service.RecommendService;

@org.springframework.stereotype.Service
public class MockRecommendServiceImpl implements RecommendService {

	@Override
	public AddReferenceResult addReference(String userId, String referenceCode, int credit) {
		return null;
	}

	@Override
	public int getRecommendSummary(String userId) {
		return 0;
	}

	@Override
	public List<RecommendInfo> getRecommendDetail(String userId) {
		return null;
	}
}
