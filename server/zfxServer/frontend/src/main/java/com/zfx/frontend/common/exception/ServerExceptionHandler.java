package com.zfx.frontend.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.common.util.JSONUtil;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.constant.MsgContants;

public class ServerExceptionHandler implements HandlerExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Request requestData = (Request) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE);
		String requestStr = null;
		if (requestData != null) {
			requestStr = JSONUtil.toJSONString(requestData);
		}
		logger.error(requestStr, ex);
		try {
			Response responseData = Response.build(Response.SERVER_INTERNAL_ERROR, MsgContants.SERVER_INTERNAL_ERROR);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(JSONUtil.toJSONString(responseData));
		} catch (Exception e) {
		}
		return new ModelAndView();
	}
}
