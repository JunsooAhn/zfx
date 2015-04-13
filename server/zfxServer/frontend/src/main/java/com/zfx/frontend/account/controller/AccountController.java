package com.zfx.frontend.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.constant.UpdateInfoState;
import com.zfx.account.service.AccountService;
import com.zfx.frontend.account.bean.request.BindPhoneRequest;
import com.zfx.frontend.account.bean.request.CheckPasswordRequest;
import com.zfx.frontend.account.bean.request.CheckPhoneRequest;
import com.zfx.frontend.account.bean.request.ReBindPhoneRequest;
import com.zfx.frontend.account.bean.request.RetrievePasswordRequest;
import com.zfx.frontend.account.bean.request.UpdateBirthdayRequest;
import com.zfx.frontend.account.bean.request.UpdateGenderRequest;
import com.zfx.frontend.account.bean.request.UpdateNicknameRequest;
import com.zfx.frontend.account.bean.request.UpdatePasswordRequest;
import com.zfx.frontend.account.bean.request.UpdateViewWebsiteRequest;
import com.zfx.frontend.account.bean.response.CheckCorrectResponse;
import com.zfx.frontend.account.bean.response.CheckExistResponse;
import com.zfx.frontend.account.bean.response.RebindPhoneResponse;
import com.zfx.frontend.account.bean.response.RetrievePasswordResponse;
import com.zfx.frontend.account.bean.response.UpdateUserInfoResponse;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.controller.BaseController;

@Controller
@RequestMapping("account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;



	@RequestMapping(value = "update/nickname")
	@ResponseBody
	public UpdateUserInfoResponse updateNickname(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateNicknameRequest updateNicknameRequest) {
		int updateInfoState = accountService.updateUserNick(updateNicknameRequest.getUserId(),
				updateNicknameRequest.getNickName());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/gender")
	@ResponseBody
	public UpdateUserInfoResponse updateGender(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateGenderRequest updateGenderRequest) {
		int updateInfoState = accountService.updateGender(updateGenderRequest.getUserId(),
				updateGenderRequest.getGender());

		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
		return updateUserInfoResponse;
	}

	@RequestMapping(value = "update/birthday")
	@ResponseBody
	public UpdateUserInfoResponse updateBirthday(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) UpdateBirthdayRequest updateBirthdayRequest) {
		int updateInfoState = accountService.updateBirthday(updateBirthdayRequest.getUserId(),
				updateBirthdayRequest.getBirthday());
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
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
		return  null;
	}

	@RequestMapping(value = "bind/phone")
	@ResponseBody
	public UpdateUserInfoResponse bindPhone(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) BindPhoneRequest bindPhoneRequest) {
		UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();

		return updateUserInfoResponse;
	}

	@RequestMapping(value = "rebind/phone")
	@ResponseBody
	public RebindPhoneResponse rebindPhone(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) ReBindPhoneRequest reBindPhoneRequest) {
		RebindPhoneResponse rebindPhoneResponse = new RebindPhoneResponse();

		return rebindPhoneResponse;
	}
}
