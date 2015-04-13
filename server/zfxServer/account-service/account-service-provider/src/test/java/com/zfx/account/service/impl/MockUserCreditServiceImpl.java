package com.zfx.account.service.impl;

import java.util.List;

import com.zfx.credit.bean.CreditObtainRecord;
import com.zfx.credit.bean.UserCreditDetail;
import com.zfx.credit.bean.UserCreditSummary;
import com.zfx.credit.bean.UserDailyIncomeStatistic;
import com.zfx.credit.bean.UserIncomeCompositeDetail;
import com.zfx.credit.service.UserCreditService;

public class MockUserCreditServiceImpl implements UserCreditService {

	@Override
	public UserCreditSummary getUserCreditSummary(String userId) {
		return null;
	}

	@Override
	public UserCreditDetail getCreditDetail(String userId) {
		return null;
	}

	@Override
	public List<UserDailyIncomeStatistic> getDailyIncomeStatistic(String userId, String startDate, String endDate) {
		return null;
	}

	@Override
	public UserIncomeCompositeDetail getUserIncomeCompositeDetail(String userId, String startDate, String endDate) {
		return null;
	}

	@Override
	public boolean synchronizeCredit(String userId, List<CreditObtainRecord> creditObtainRecords) {
		return false;
	}

	@Override
	public boolean obtainCredit(String userId, CreditObtainRecord creditObtainRecord) {
		return false;
	}

	@Override
	public boolean initCredit(String userId) {
		return false;
	}

	@Override
	public int getSingleTypeCredit(String userId, int actionType) {
		return 0;
	}

	@Override
	public boolean obtainCredit(String userId, int credit, int actionType) {
		return false;
	}
}
