package com.moneylocker.frontend.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moneylocker.common.util.AESTools;
import com.moneylocker.common.util.JSONUtil;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;
import com.moneylocker.frontend.common.constant.CommonConstant;
import com.moneylocker.frontend.common.constant.MsgContants;

public class DecryptInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(DecryptInterceptor.class);

	@SuppressWarnings("rawtypes")
	private static Map<String, Class> requestDtoMap = new HashMap<String, Class>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String param = request.getParameter(CommonConstant.REQUEST_PARAM);
		String contextPath = request.getContextPath();
		String requestUri = request.getRequestURI();
		String locationPath = requestUri.replaceFirst(contextPath, "");

		Class<?> requestType = requestDtoMap.get(locationPath);

		// 获取参数Class类型
		if (requestType == null || param == null) {
			response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "The request is not supported");
			logger.error("{} with param {} is not supported", locationPath, param);
			return false;
		}

		Request paramRequest = null;
		Response responseData = null;
		// 解密参数，并且转华为对应类型
		try {
			paramRequest = (Request) AESTools.decrypt(param, requestType);
		} catch (Exception e) {
			logger.error("{} with param {} is illegal", locationPath, param);
			responseData = Response.build(Response.PARAM_PARSE_ERROR, MsgContants.PARAM_PARSE_ERROR);
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(JSONUtil.toJSONString(responseData));
			return false;
		}

		// 验证参数的基本合法性
		responseData = paramRequest.validate();
		if (responseData != null) {
			logger.error("{} with param {} is invalid", locationPath, JSONUtil.toJSONString(paramRequest));
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(JSONUtil.toJSONString(responseData));
			return false;
		}

		// 把最终参数写到request的attribute中供controller使用
		request.setAttribute(CommonConstant.REQUEST_ATTRIBUTE, paramRequest);
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

	public static <T> void register(String url, Class<T> cls) {
		requestDtoMap.put(url, cls);
	}
}
