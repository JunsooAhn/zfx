package com.zfx.frontend.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.constant.CommonConstant;

public class BaseController {

	@ModelAttribute
	public void putRquest(HttpServletRequest request, Model model) {
		Request requestData = (Request) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE);
		model.addAttribute(CommonConstant.MODEL_ATTRIBUTE, requestData);
	}
}
