package com.moneylocker.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.bean.AddReferenceResult;
import com.moneylocker.account.bean.RecommendInfo;
import com.moneylocker.account.constant.AddReferencesState;
import com.moneylocker.account.constant.UserInfoType;
import com.moneylocker.account.constant.UserPartInfoType;
import com.moneylocker.account.dao.RecommendLogDao;
import com.moneylocker.account.dao.UserInfoDao;
import com.moneylocker.account.service.RecommendService;
import com.moneylocker.common.util.StringUtils;

@Service(value = "recommendService")
@Transactional(value = "serviceTxManager")
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private RecommendLogDao recommendLogDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AddReferenceResult addReference(String userId, String referenceCode, int credit) {
		Map<String, Object> rPartInfoMap = userInfoDao.getPartInfoByUserId(userId, UserPartInfoType.RECOMMEND);
		if (rPartInfoMap == null) {
			return new AddReferenceResult(AddReferencesState.USER_NOT_EXIST);
		}

		String rPhone = (String) rPartInfoMap.get("phone");
		String rUserRecommendCode = (String) rPartInfoMap.get("recommendCode");
		String userOrigin = (String) rPartInfoMap.get("userOrigin");
		if (!StringUtils.isEmpty(userOrigin)) {
			return new AddReferenceResult(AddReferencesState.ORIGIN_ALREAY_FILLED);
		}

		Map<String, Object> rdPartInfoMap = userInfoDao.getPartInfoByRecommendCode(referenceCode,
				UserPartInfoType.RECOMMEND);
		if (rdPartInfoMap == null) {
			return new AddReferenceResult(AddReferencesState.REFERENCE_CODE_NOT_EXIST);
		}

		String rdUserId = (String) rdPartInfoMap.get("userId");
		referenceCode = (String) rdPartInfoMap.get("recommendedCode");
		if (userId.equals(rdUserId)) {
			return new AddReferenceResult(AddReferencesState.CAN_NOT_FILL_SELF);
		}

		boolean updateResult = userInfoDao.updateUserInfoSinglePropertyWithCheck(userId, UserInfoType.ORIGIN,
				referenceCode, null);
		if (!updateResult) {
			return new AddReferenceResult(AddReferencesState.ORIGIN_ALREAY_FILLED);
		}

		recommendLogDao.addRecommendLog(userId, rPhone, rUserRecommendCode, rdUserId, credit);

		// TODO 发送推荐行为的日志，来进行推荐作弊的判断
		return new AddReferenceResult(AddReferencesState.SUCCESS, rdUserId);
	}

	@Override
	@Transactional(readOnly = true)
	public int getRecommendSummary(String userId) {
		return recommendLogDao.getRecommendSummary(userId);
	}

	@Override
	public List<RecommendInfo> getRecommendDetail(String userId) {
		return recommendLogDao.getRecommendDetail(userId);
	}
}
