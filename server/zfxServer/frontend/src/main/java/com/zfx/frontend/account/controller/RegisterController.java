package com.zfx.frontend.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.bean.LoginResult;
import com.zfx.account.constant.LoginResultState;
import com.zfx.account.constant.UserVerifyType;
import com.zfx.account.service.LoginService;
import com.zfx.common.service.MemcachedService;
import com.zfx.credit.constant.CreditOperations;
import com.zfx.credit.service.UserCreditService;
import com.zfx.frontend.account.bean.request.RegisterRequest;
import com.zfx.frontend.account.bean.response.RegisterResponse;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.ActivityCreditConfig;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.bean.VerifyInfo;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.constant.MemcacheKeyConstants;
import com.zfx.frontend.common.controller.BaseController;

@Controller
@RequestMapping("register")
public class RegisterController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserCreditService userCreditService;

	@Autowired
	private MemcachedService memcachedService;

	@Autowired
	private ActivityCreditConfig activityCreditConfig;

	@RequestMapping(value = "")
	@ResponseBody
	public RegisterResponse register(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) RegisterRequest registerRequest) {
		String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + registerRequest.getPhone();
		VerifyInfo verifyInfo = (VerifyInfo) memcachedService.memcachedGet(memcacheKey);
		RegisterResponse registerResponse = new RegisterResponse();

		if (verifyInfo == null || !verifyInfo.getVerifyCode().equals(registerRequest.getVerifyCode())) {
			registerResponse.setStatusCode(RegisterResponse.VERIFY_ERROR);
			registerResponse.setMsg(MsgContants.REGISTER_VERIFY_ERROR);
			return registerResponse;
		}

		LoginResult loginResult = loginService.registerNewUser(registerRequest.getImei(), registerRequest.getPhone(),
				registerRequest.getPassword(), (short) verifyInfo.getVerifyType(), registerRequest.getModel(),
				registerRequest.getManufacturer(), registerRequest.getOsVersion(), registerRequest.getAppVersion(),
				registerRequest.getAndroidId(), registerRequest.getMacAddress());

		if (loginResult.getLoginResultState() == LoginResultState.SUCCESS) {
			registerResponse.setStatusCode(Response.SUCCESS);
			registerResponse.setLoginId(loginResult.getLoginId());
			registerResponse.setUserInfo(loginResult.getUserInfo());
			registerResponse.setMsg(MsgContants.REGISTER_SUCCESS);
			int credit = activityCreditConfig.getRegisterSmsCredit();
			if (verifyInfo.getVerifyType() == UserVerifyType.VOICE) {
				credit = activityCreditConfig.getRegisterVoiceCredit();
			}
			// 忽略可能的异常，保证注册成功
			try {
				userCreditService.obtainCredit(loginResult.getUserInfo().getUserId(), credit,
						CreditOperations.ACTIVITY);
			} catch (Exception e) {
				logger.error("Fail to add register credit to " + loginResult.getUserInfo().getUserId(), e);
			}
		} else if (loginResult.getLoginResultState() == LoginResultState.PHONE_EXISTS) {
			registerResponse.setStatusCode(RegisterResponse.PHONE_EXISTS);
			registerResponse.setMsg(MsgContants.REGISTER_DUPLICATED);
		} else {
			registerResponse.setStatusCode(Response.FAILED);
			registerResponse.setMsg(MsgContants.REGISTER_FAILD);
		}
		return registerResponse;
	}
}
