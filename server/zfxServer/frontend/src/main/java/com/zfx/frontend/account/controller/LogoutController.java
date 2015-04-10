package com.moneylocker.frontend.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moneylocker.account.constant.LoginResultState;
import com.moneylocker.account.service.LoginService;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;
import com.moneylocker.frontend.common.constant.CommonConstant;
import com.moneylocker.frontend.common.controller.BaseController;

@Controller
@RequestMapping("logout")
public class LogoutController extends BaseController {


	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "")
	@ResponseBody
	public Response logout(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) Request request) {
		int status = loginService.logout(request.getUserId(), request.getLoginId());
		if (LoginResultState.SUCCESS == status) {
			return Response.build(Response.SUCCESS, MsgContants.LOGOUT_SUCCESS);
		}
		return Response.build(Response.FAILED, MsgContants.LOGOUT_FAILED);

	}
}
