package com.zfx.frontend.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfx.account.service.LoginService;
import com.zfx.frontend.account.bean.request.RegisterRequest;
import com.zfx.frontend.account.bean.response.RegisterResponse;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.controller.BaseController;

@Controller
@RequestMapping("register")
public class RegisterController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private LoginService loginService;


	@RequestMapping(value = "")
	@ResponseBody
	public RegisterResponse register(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) RegisterRequest registerRequest) {
		RegisterResponse registerResponse = new RegisterResponse();
		loginService.registerNewUser("1", "1", "1",null, null, "1", "1", 1, "1", "1");
		return registerResponse;
	}
}
