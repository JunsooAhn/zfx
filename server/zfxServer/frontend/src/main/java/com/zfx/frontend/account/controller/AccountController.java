package com.moneylocker.frontend.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moneylocker.account.constant.RebindPhoneState;
import com.moneylocker.account.constant.UpdateInfoState;
import com.moneylocker.account.constant.UserVerifyType;
import com.moneylocker.account.service.AccountService;
import com.moneylocker.common.service.MemcachedService;
import com.moneylocker.credit.constant.CreditOperations;
import com.moneylocker.credit.service.UserCreditService;
import com.moneylocker.frontend.account.bean.request.BindPhoneRequest;
import com.moneylocker.frontend.account.bean.request.CheckPasswordRequest;
import com.moneylocker.frontend.account.bean.request.CheckPhoneRequest;
import com.moneylocker.frontend.account.bean.request.ReBindPhoneRequest;
import com.moneylocker.frontend.account.bean.request.RetrievePasswordRequest;
import com.moneylocker.frontend.account.bean.request.UpdateBirthdayRequest;
import com.moneylocker.frontend.account.bean.request.UpdateGenderRequest;
import com.moneylocker.frontend.account.bean.request.UpdateNicknameRequest;
import com.moneylocker.frontend.account.bean.request.UpdatePasswordRequest;
import com.moneylocker.frontend.account.bean.request.UpdateViewWebsiteRequest;
import com.moneylocker.frontend.account.bean.response.CheckCorrectResponse;
import com.moneylocker.frontend.account.bean.response.CheckExistResponse;
import com.moneylocker.frontend.account.bean.response.RebindPhoneResponse;
import com.moneylocker.frontend.account.bean.response.RetrievePasswordResponse;
import com.moneylocker.frontend.account.bean.response.UpdateUserInfoResponse;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.AccountConfig;
import com.moneylocker.frontend.common.bean.ActivityCreditConfig;
import com.moneylocker.frontend.common.bean.Response;
import com.moneylocker.frontend.common.bean.VerifyInfo;
import com.moneylocker.frontend.common.constant.CommonConstant;
import com.moneylocker.frontend.common.constant.MemcacheKeyConstants;
import com.moneylocker.frontend.common.controller.BaseController;

@Controller
@RequestMapping("account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	private MemcachedService memcachedService;

	@Autowired
	private ActivityCreditConfig activityCreditConfig;

	@Autowired
	private AccountConfig accountConfig;

	@RequestMapping(value = "update/nickname")
	@ResponseBody
	public UpdateUserInfoResponse updateNickname(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateNicknameRequest updateNicknameRequest) {
		int updateInfoState = accountService.updateUserNick(updateNicknameRequest.getUserId(),
				updateNicknameRequest.getNickName());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		if (updateInfoState == UpdateInfoState.NICK_NAME_EXIST) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.NICK_NAME_EXIST);
			updateUserInfoResponse.setMsg(MsgContants.NICK_NAME_EXIST);
		} else if (updateInfoState == UpdateInfoState.SUCCESS_FIRST_TIME) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS_FIRST_TIME);
			// 第一次更新，添加积分
			if (userCreditService.obtainCredit(updateNicknameRequest.getUserId(),
					activityCreditConfig.getUpdateNicknameCredit(),
					CreditOperations.ACTIVITY)) {
				updateUserInfoResponse.setMsg(MsgContants.NICK_NAME_UPDATE_WITH_CREDIT
						+ activityCreditConfig.getUpdateNicknameCredit() * 0.01 + "元！");
			} else {
				updateUserInfoResponse.setMsg(MsgContants.NICK_NAME_UPDATE_SUCCESS);
			}
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.NICK_NAME_UPDATE_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.NICK_NAME_UPDATE_FAILD);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/gender")
	@ResponseBody
	public UpdateUserInfoResponse updateGender(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateGenderRequest updateGenderRequest) {
		int updateInfoState = accountService.updateGender(updateGenderRequest.getUserId(),
				updateGenderRequest.getGender());

		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		if (updateInfoState == UpdateInfoState.SUCCESS_FIRST_TIME) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS_FIRST_TIME);
			// 第一次更新，添加积分
			if (userCreditService.obtainCredit(updateGenderRequest.getUserId(),
					activityCreditConfig.getUpdateGenderCredit(),
					CreditOperations.ACTIVITY)) {
				updateUserInfoResponse.setMsg(MsgContants.GENDER_UPDATE_WITH_CREDIT
						+ activityCreditConfig.getUpdateGenderCredit()
 * 0.01 + "元！");
			} else {
				updateUserInfoResponse.setMsg(MsgContants.GENDER_UPDATE_SUCCESS);
			}
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.GENDER_UPDATE_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.GENDER_UPDATE_FAILD);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/birthday")
	@ResponseBody
	public UpdateUserInfoResponse updateBirthday(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateBirthdayRequest updateBirthdayRequest) {
		int updateInfoState = accountService.updateBirthday(updateBirthdayRequest.getUserId(),
				updateBirthdayRequest.getBirthday());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		if (updateInfoState == UpdateInfoState.SUCCESS_FIRST_TIME) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS_FIRST_TIME);
			// 第一次更新，添加积分
			if (userCreditService.obtainCredit(updateBirthdayRequest.getUserId(),
					activityCreditConfig.getUpdateBirthdayCredit(),
					CreditOperations.ACTIVITY)) {
				updateUserInfoResponse.setMsg(MsgContants.BIRTHDAY_UPDATE_WITH_CREDIT
						+ activityCreditConfig.getUpdateBirthdayCredit() * 0.01 + "元！");
			} else {
				updateUserInfoResponse.setMsg(MsgContants.BIRTHDAY_UPDATE_SUCCESS);
			}
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.BIRTHDAY_UPDATE_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.BIRTHDAY_UPDATE_FAILD);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/password")
	@ResponseBody
	public UpdateUserInfoResponse updatePassword(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdatePasswordRequest updatePasswordRequest) {
		int updateInfoState = accountService.updatePassword(updatePasswordRequest.getUserId(),
				updatePasswordRequest.getNewPassword(), updatePasswordRequest.getOldPassword());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		if (updateInfoState == UpdateInfoState.PASSWORD_WRONG) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.PASSWORD_WRONG);
			updateUserInfoResponse.setMsg(MsgContants.OLD_PASSWORD_WRNONG);
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.UPDATE_PASSWORD_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.UPDATE_PASSWORD_ERROR);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/viewWebsite")
	@ResponseBody
	public UpdateUserInfoResponse updateViewWebsite(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateViewWebsiteRequest updateViewWebsiteRequest) {
		int updateInfoState = accountService.updateViewWebsite(updateViewWebsiteRequest.getUserId());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		if (updateInfoState == UpdateInfoState.SUCCESS_FIRST_TIME) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS_FIRST_TIME);
			// 第一次更新，添加积分
			if (userCreditService.obtainCredit(updateViewWebsiteRequest.getUserId(),
					activityCreditConfig.getViewWebsiteCredit(),
					CreditOperations.ACTIVITY)) {
				updateUserInfoResponse.setMsg(MsgContants.VIEW_WEBSITE_UPDATE_WITH_CREDIT
						+ activityCreditConfig.getViewWebsiteCredit() * 0.01 + "元！");
			} else {
				updateUserInfoResponse.setMsg(MsgContants.VIEW_WEBSITE_UPDATE_SUCCESS);
			}
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.VIEW_WEBSITE_UPDATE_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.VIEW_WEBSITE_UPDATE_FAILD);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "check/password")
	@ResponseBody
	public CheckCorrectResponse checkPassword(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) CheckPasswordRequest passwordRequest) {
		boolean checkState = accountService.checkPassword(passwordRequest.getUserId(), passwordRequest.getPassword());
		CheckCorrectResponse checkCorrectResponse = new CheckCorrectResponse();
		if (checkState) {
			checkCorrectResponse.setStatusCode(CheckCorrectResponse.CORRECT);
			checkCorrectResponse.setMsg(MsgContants.CHECK_PASSWORD_SUCCESS);
		} else {
			checkCorrectResponse.setStatusCode(CheckCorrectResponse.NOT_CORRECT);
			checkCorrectResponse.setMsg(MsgContants.CHECK_PASSWORD_ERROR);
		}
		return checkCorrectResponse;
	}

	@RequestMapping(value = "is/phone/exist")
	@ResponseBody
	public CheckExistResponse checkPhone(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) CheckPhoneRequest phoneRequest) {
		boolean checkState = accountService.checkPhoneExist(phoneRequest.getPhone());
		CheckExistResponse checkExistResponse = new CheckExistResponse();
		if (checkState) {
			checkExistResponse.setStatusCode(CheckExistResponse.EXIST);
			checkExistResponse.setMsg(MsgContants.CHECK_PHONE_EXIST);
		} else {
			checkExistResponse.setStatusCode(CheckExistResponse.NOT_EXIST);
			checkExistResponse.setMsg(MsgContants.CHECK_PHONE_NOT_EXIST);
		}
		return checkExistResponse;
	}

	@RequestMapping(value = "retrieve/password")
	@ResponseBody
	public RetrievePasswordResponse findbackPassword(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) RetrievePasswordRequest retrievePasswordRequest) {
		String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + retrievePasswordRequest.getPhone();
		VerifyInfo verifyInfo = (VerifyInfo) memcachedService.memcachedGet(memcacheKey);
		RetrievePasswordResponse retrievePasswordResponse = new RetrievePasswordResponse();
		if (verifyInfo == null || !verifyInfo.getVerifyCode().equals(retrievePasswordRequest.getVerifyCode())) {
			retrievePasswordResponse.setStatusCode(RetrievePasswordResponse.VERIFY_ERROR);
			retrievePasswordResponse.setMsg(MsgContants.RETRIEVE_PASSWORD_VERIFY_ERROR);
		} else {
			int updateInfoState = accountService.findBackPassword(retrievePasswordRequest.getImei(),
					retrievePasswordRequest.getPhone(), retrievePasswordRequest.getPassword());
			if (updateInfoState == UpdateInfoState.USER_NOT_EXIST) {
				retrievePasswordResponse.setStatusCode(RetrievePasswordResponse.PHONE_NOT_EXIST);
				retrievePasswordResponse.setMsg(MsgContants.RETRIEVE_PASSWORD_USER_NOT_EXIST);
			} else if (updateInfoState == UpdateInfoState.SUCCESS) {
				retrievePasswordResponse.setStatusCode(Response.SUCCESS);
				retrievePasswordResponse.setMsg(MsgContants.RETRIEVE_PASSWORD_SUCCESS);
			} else {
				retrievePasswordResponse.setStatusCode(Response.FAILED);
				retrievePasswordResponse.setMsg(MsgContants.RETRIEVE_PASSWORD_FAILED);
			}
		}
		return retrievePasswordResponse;
	}

	@RequestMapping(value = "bind/phone")
	@ResponseBody
	public UpdateUserInfoResponse bindPhone(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) BindPhoneRequest bindPhoneRequest) {
		String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + bindPhoneRequest.getPhone();
		VerifyInfo verifyInfo = (VerifyInfo) memcachedService.memcachedGet(memcacheKey);
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();

		if (verifyInfo == null || !verifyInfo.getVerifyCode().equals(bindPhoneRequest.getVerifyCode())) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.VERIFY_ERROR);
			updateUserInfoResponse.setMsg(MsgContants.BIND_PHONE_VERIFY_ERROR);
			return updateUserInfoResponse;
		}
		int updateInfoState = accountService.bindPhone(bindPhoneRequest.getUserId(), bindPhoneRequest.getPhone(),
				bindPhoneRequest.getPassword(), (short) verifyInfo.getVerifyType());

		if (updateInfoState == UpdateInfoState.SUCCESS_FIRST_TIME) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS_FIRST_TIME);
			// 第一次更新，添加积分
			int credit = activityCreditConfig.getRegisterSmsCredit();
			if (verifyInfo.getVerifyType() == UserVerifyType.VOICE) {
				credit = activityCreditConfig.getRegisterVoiceCredit();
			}
			if (userCreditService.obtainCredit(bindPhoneRequest.getUserId(), credit, CreditOperations.ACTIVITY)) {
				updateUserInfoResponse.setMsg(MsgContants.BIND_PHONE_SUCCESS_WITH_CREDIT + credit * 0.01 + "元！");
			} else {
				updateUserInfoResponse.setMsg(MsgContants.BIND_PHONE_SUCCESS);
			}
		} else if (updateInfoState == UpdateInfoState.SUCCESS) {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.SUCCESS);
			updateUserInfoResponse.setMsg(MsgContants.BIND_PHONE_SUCCESS);
		} else {
			updateUserInfoResponse.setStatusCode(UpdateUserInfoResponse.FAILED);
			updateUserInfoResponse.setMsg(MsgContants.BIND_PHONE_ERROR);
		}
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "rebind/phone")
	@ResponseBody
	public RebindPhoneResponse rebindPhone(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) ReBindPhoneRequest reBindPhoneRequest) {
		String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + reBindPhoneRequest.getPhone();
		VerifyInfo verifyInfo = (VerifyInfo) memcachedService.memcachedGet(memcacheKey);
		RebindPhoneResponse rebindPhoneResponse = new RebindPhoneResponse();

		if (verifyInfo == null || !verifyInfo.getVerifyCode().equals(reBindPhoneRequest.getVerifyCode())) {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.VERIFY_ERROR);
			rebindPhoneResponse.setMsg(MsgContants.REBIND_PHONE_VERIFY_ERROR);
			return rebindPhoneResponse;
		}
		int rebindPhoneState = accountService.rebindPhone(reBindPhoneRequest.getUserId(),
				reBindPhoneRequest.getPhone(), reBindPhoneRequest.getOldPhone(), reBindPhoneRequest.getPassword(),
				(short) verifyInfo.getVerifyType(), accountConfig.getAllowRebindAfterDays());

		if (rebindPhoneState == RebindPhoneState.SUCCESS) {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.SUCCESS);
			rebindPhoneResponse.setMsg(MsgContants.REBIND_PHONE_SUCCESS);
		} else if (rebindPhoneState == RebindPhoneState.OLD_PHONE_ERROR) {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.OLD_PHONE_WRONG);
			rebindPhoneResponse.setMsg(MsgContants.REBIND_PHONE_OLD_PHONE_WRONG);
		} else if (rebindPhoneState == RebindPhoneState.PASSWORD_WRONG) {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.PASSWORD_WRONG);
			rebindPhoneResponse.setMsg(MsgContants.REBIND_PHONE_PASSWORD_WRONG);
		} else if (rebindPhoneState == RebindPhoneState.BIND_TOO_FRENQUENTLY) {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.FAILED);
			rebindPhoneResponse.setMsg(String.format(MsgContants.REBIND_TOO_FREQUENTLY,
					accountConfig.getAllowRebindAfterDays()));
		} else {
			rebindPhoneResponse.setStatusCode(RebindPhoneResponse.FAILED);
			rebindPhoneResponse.setMsg(MsgContants.REBIND_PHONE_ERROR);
		}
		return rebindPhoneResponse;
	}
}
