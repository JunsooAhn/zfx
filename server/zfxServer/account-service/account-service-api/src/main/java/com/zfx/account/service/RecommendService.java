package com.zfx.account.service;

import java.util.List;

import com.zfx.account.bean.AddReferenceResult;
import com.zfx.account.bean.RecommendInfo;


public interface RecommendService {

	public AddReferenceResult addReference(String userId, String referenceCode, int credit);

	public int getRecommendSummary(String userId);

	public List<RecommendInfo> getRecommendDetail(String userId);
}
