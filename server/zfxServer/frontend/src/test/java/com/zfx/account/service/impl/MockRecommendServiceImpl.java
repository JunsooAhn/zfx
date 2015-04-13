package com.zfx.account.service.impl;

import java.util.List;

import com.zfx.account.bean.AddReferenceResult;
import com.zfx.account.bean.RecommendInfo;
import com.zfx.account.service.RecommendService;

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
