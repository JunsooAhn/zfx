package com.zfx.frontend.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.constant.LoginResultState;
import com.zfx.account.service.LoginService;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.controller.BaseController;

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
