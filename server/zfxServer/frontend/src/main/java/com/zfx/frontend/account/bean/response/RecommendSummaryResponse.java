package com.moneylocker.frontend.account.bean.response;

import com.moneylocker.frontend.common.bean.Response;

public class RecommendSummaryResponse extends Response {

	private int currentReward;

	private int totalCredit;

	private int num;

	public int getCurrentReward() {
		return currentReward;
	}

	public void setCurrentReward(int currentReward) {
		this.currentReward = currentReward;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
