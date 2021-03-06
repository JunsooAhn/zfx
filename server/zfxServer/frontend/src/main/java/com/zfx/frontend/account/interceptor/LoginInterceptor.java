package com.zfx.frontend.account.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.account.constant.LoginState;
import com.zfx.account.service.LoginService;
import com.zfx.common.util.JSONUtil;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Request requestData = (Request) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE);
		if (requestData != null) {
			int loginState = loginService.getLoginState(requestData.getLoginId(), requestData.getUserId());
			if (loginState == LoginState.NOT_LOGIN) {
				Response responseData = new Response();
				responseData.setStatusCode(Response.NOT_LOGIN);
				response.getWriter().write(JSONUtil.toJSONString(responseData));
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
