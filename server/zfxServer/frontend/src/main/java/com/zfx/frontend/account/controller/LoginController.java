package com.zfx.frontend.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.bean.LoginResult;
import com.zfx.account.constant.LoginResultState;
import com.zfx.account.service.LoginService;
import com.zfx.frontend.account.bean.request.DeviceLoginRequest;
import com.zfx.frontend.account.bean.request.ImeiLoginRequest;
import com.zfx.frontend.account.bean.request.PhoneLoginRequest;
import com.zfx.frontend.account.bean.response.PhoneLoginResponse;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.controller.BaseController;

@Controller
@RequestMapping("login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "phone")
	@ResponseBody
	public PhoneLoginResponse loginByPhone(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) PhoneLoginRequest phoneLoginRequest) {
		LoginResult loginResult = loginService.loginByPhone(phoneLoginRequest.getPhone(),
				phoneLoginRequest.getPassword(), phoneLoginRequest.getImei(), phoneLoginRequest.getModel(),
				phoneLoginRequest.getManufacturer(), phoneLoginRequest.getOsVersion(),
				phoneLoginRequest.getAppVersion(), phoneLoginRequest.getAndroidId(),
				phoneLoginRequest.getMacAddress());

		PhoneLoginResponse phoneLoginResponse = new PhoneLoginResponse();
		if (loginResult.getLoginResultState() == LoginResultState.SUCCESS) {
			phoneLoginResponse.setStatusCode(Response.SUCCESS);
			phoneLoginResponse.setLoginId(loginResult.getLoginId());
			phoneLoginResponse.setUserInfo(loginResult.getUserInfo());
			phoneLoginResponse.setMsg(MsgContants.LOGIN_SUCCESS);
		} else if (loginResult.getLoginResultState() == LoginResultState.PASSWORD_INVALID) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.PASSWORD_INCORRECT);
			phoneLoginResponse.setMsg(MsgContants.LOGIN_PASSWORD_INCORRECT);
		} else if (loginResult.getLoginResultState() == LoginResultState.ACCOUNT_FREEZE) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.ACCOUNT_FREEZE);
			phoneLoginResponse.setMsg(MsgContants.LOGIN_ACCOUNT_FREEZE);
		} else if (loginResult.getLoginResultState() == LoginResultState.ACCOUNT_CHANGE_FREQUENTLY) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.ACCOUNT_CHANGE_FREQUENTLY);
			phoneLoginResponse.setMsg(MsgContants.LOGIN_ACCOUNT_CHANGE_FREQUENTLY);
		}
		return phoneLoginResponse;
	}

	@RequestMapping(value = "imei")
	@ResponseBody
	@Deprecated
	public PhoneLoginResponse loginByImei(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) ImeiLoginRequest phoneLoginRequest) {
		LoginResult loginResult = loginService.loginByImei(phoneLoginRequest.getImei(), phoneLoginRequest.getModel(),
				phoneLoginRequest.getManufacturer(), phoneLoginRequest.getOsVersion(),
				phoneLoginRequest.getAppVersion());

		PhoneLoginResponse phoneLoginResponse = new PhoneLoginResponse();
		if (loginResult.getLoginResultState() == LoginResultState.SUCCESS) {
			phoneLoginResponse.setStatusCode(Response.SUCCESS);
			phoneLoginResponse.setLoginId(loginResult.getLoginId());
			phoneLoginResponse.setUserInfo(loginResult.getUserInfo());
			phoneLoginResponse.setMsg(MsgContants.LOGIN_SUCCESS);
		} else if (loginResult.getLoginResultState() == LoginResultState.IEMI_ALREADY_EXISTS) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.IMEI_DUPLICATE);
			phoneLoginResponse.setMsg(MsgContants.LOGIN_IEMI_ALREADY_EXISTS);
		} else if (loginResult.getLoginResultState() == LoginResultState.REGISTER_SUCCESS) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.ACCOUNT_CREATE_SUCCESS);
			phoneLoginResponse.setLoginId(loginResult.getLoginId());
			phoneLoginResponse.setUserInfo(loginResult.getUserInfo());
			phoneLoginResponse.setMsg(MsgContants.LOGIN_REGISTER_SUCCESS);
		}
		return phoneLoginResponse;
	}

	@RequestMapping(value = "device")
	@ResponseBody
	public PhoneLoginResponse loginByDevice(
			@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) DeviceLoginRequest phoneLoginRequest) {
		LoginResult loginResult = loginService.loginByDevice(phoneLoginRequest.getImei(),
				phoneLoginRequest.getAndroidId(), phoneLoginRequest.getMacAddress());

		PhoneLoginResponse phoneLoginResponse = new PhoneLoginResponse();
		if (loginResult.getLoginResultState() == LoginResultState.SUCCESS) {
			phoneLoginResponse.setStatusCode(Response.SUCCESS);
			phoneLoginResponse.setLoginId(loginResult.getLoginId());
			phoneLoginResponse.setUserInfo(loginResult.getUserInfo());
			phoneLoginResponse.setMsg(MsgContants.LOGIN_SUCCESS);
		} else if (loginResult.getLoginResultState() == LoginResultState.IEMI_ALREADY_EXISTS) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.IMEI_DUPLICATE);
			phoneLoginResponse.setMsg(MsgContants.LOGIN_IEMI_ALREADY_EXISTS);
		} else if (loginResult.getLoginResultState() == LoginResultState.REGISTER_SUCCESS) {
			phoneLoginResponse.setStatusCode(PhoneLoginResponse.ACCOUNT_CREATE_SUCCESS);
			phoneLoginResponse.setLoginId(loginResult.getLoginId());
			phoneLoginResponse.setUserInfo(loginResult.getUserInfo());
			phoneLoginResponse.setMsg(MsgContants.LOGIN_REGISTER_SUCCESS);
		}
		return phoneLoginResponse;
	}

}
