package com.zfx.frontend.account.bean.response;

import java.util.List;

import com.zfx.account.bean.RecommendInfo;
import com.zfx.frontend.common.bean.Response;

public class RecommendDetailResponse extends Response {

	private int totalCredit;

	private List<RecommendInfo> recommendInfoList;

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public List<RecommendInfo> getRecommendInfoList() {
		return recommendInfoList;
	}

	public void setRecommendInfoList(List<RecommendInfo> recommendInfoList) {
		this.recommendInfoList = recommendInfoList;
	}
}
