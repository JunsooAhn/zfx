package com.zfx.account.dao;

import java.util.List;

import com.zfx.account.bean.RecommendInfo;

public interface RecommendLogDao {

	public void addRecommendLog(String rUserId, String rPhone, String rRecommendCode, String rdUserId, int credit);

	public int getRecommendSummary(String userId);

	public List<RecommendInfo> getRecommendDetail(String userId);
}
