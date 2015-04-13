package com.zfx.frontend.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.bean.AddReferenceResult;
import com.zfx.account.bean.RecommendInfo;
import com.zfx.account.constant.AddReferencesState;
import com.zfx.account.service.RecommendService;
import com.zfx.credit.constant.CreditOperations;
import com.zfx.credit.service.UserCreditService;
import com.zfx.frontend.account.bean.request.RecommendAddRequest;
import com.zfx.frontend.account.bean.response.RecommendAddResponse;
import com.zfx.frontend.account.bean.response.RecommendDetailResponse;
import com.zfx.frontend.account.bean.response.RecommendSummaryResponse;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.ActivityCreditConfig;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.controller.BaseController;

@Controller
@RequestMapping("recommend")
public class RecommendController extends BaseController {

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	private ActivityCreditConfig activityCreditConfig;

	@RequestMapping(value = "add")
	@ResponseBody
	public RecommendAddResponse addRecommend(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) RecommendAddRequest recommendAddRequest) {
		AddReferenceResult addReferenceResult = recommendService.addReference(recommendAddRequest.getUserId(),
				recommendAddRequest.getReferenceCode(), activityCreditConfig.getBeRecommendedCredit());
		RecommendAddResponse recommendAddResponse = new RecommendAddResponse();
		if (addReferenceResult.getState() == AddReferencesState.SUCCESS) {
			// 添加积分
			userCreditService.obtainCredit(recommendAddRequest.getUserId(), activityCreditConfig.getRecommendCredit(),
					CreditOperations.ACTIVITY);
			userCreditService.obtainCredit(addReferenceResult.getUserId(),
					activityCreditConfig.getBeRecommendedCredit(),
					CreditOperations.RECOMMENDED);
			recommendAddResponse.setStatusCode(Response.SUCCESS);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_SUCCESS);
		} else if (addReferenceResult.getState() == AddReferencesState.CAN_NOT_FILL_SELF) {
			recommendAddResponse.setStatusCode(RecommendAddResponse.CAN_NOT_FILL_SELF);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_CAN_NOT_FILL_SELF);
		} else if (addReferenceResult.getState() == AddReferencesState.ORIGIN_ALREAY_FILLED) {
			recommendAddResponse.setStatusCode(RecommendAddResponse.ORIGIN_ALREAY_FILLED);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_ORIGIN_ALREAY_FILLED);
		} else if (addReferenceResult.getState() == AddReferencesState.REFERENCE_CODE_NOT_EXIST) {
			recommendAddResponse.setStatusCode(RecommendAddResponse.REFERENCE_CODE_NOT_EXIST);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_REFERENCE_CODE_NOT_EXIST);
		} else if (addReferenceResult.getState() == AddReferencesState.USER_NOT_EXIST) {
			recommendAddResponse.setStatusCode(RecommendAddResponse.USER_NOT_EXIST);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_USER_NOT_EXIST);
		} else {
			recommendAddResponse.setStatusCode(RecommendAddResponse.FAILED);
			recommendAddResponse.setMsg(MsgContants.RECOMMEND_ERROR);
		}
		return recommendAddResponse;
	}

	@RequestMapping(value = "summary")
	@ResponseBody
	public RecommendSummaryResponse getSummary(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) Request summaryRequest) {
		RecommendSummaryResponse recommendSummaryResponse = new RecommendSummaryResponse();
		int num = recommendService.getRecommendSummary(summaryRequest.getUserId());
		recommendSummaryResponse.setNum(num);
		recommendSummaryResponse.setCurrentReward(activityCreditConfig.getBeRecommendedCredit());
		recommendSummaryResponse.setTotalCredit(userCreditService.getSingleTypeCredit(summaryRequest.getUserId(),
				CreditOperations.RECOMMENDED));
		recommendSummaryResponse.setStatusCode(Response.SUCCESS);
		return recommendSummaryResponse;
	}

	@RequestMapping(value = "detail")
	@ResponseBody
	public RecommendDetailResponse getDetail(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) Request detailRequest) {
		RecommendDetailResponse recommendDetailResponse = new RecommendDetailResponse();
		List<RecommendInfo> recommendInfoList = recommendService.getRecommendDetail(detailRequest.getUserId());
		recommendDetailResponse.setRecommendInfoList(recommendInfoList);
		recommendDetailResponse.setTotalCredit(userCreditService.getSingleTypeCredit(detailRequest.getUserId(),
				CreditOperations.RECOMMENDED));
		recommendDetailResponse.setStatusCode(Response.SUCCESS);
		return recommendDetailResponse;
	}
}
