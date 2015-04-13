package com.zfx.frontend.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zfx.common.service.MemcachedService;
import com.zfx.common.util.JSONUtil;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;
import com.zfx.frontend.common.constant.CommonConstant;
import com.zfx.frontend.common.constant.MsgContants;

public class DuplicateRequestInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(DuplicateRequestInterceptor.class);

	private MemcachedService memcachedService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Request requestData = (Request) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE);
		if (requestData != null) {
			String duplicateKey = requestData.getDuplicateKey();
			if (duplicateKey != null) {
				String requestUri = request.getRequestURI();
				String memcacheKey = requestUri + ":" + duplicateKey;
				if (!memcachedService.memcachedLock(memcacheKey)) {
					logger.error("{} with memcache key {} is duplicate", requestUri, memcacheKey);
					Response responseData = Response.build(Response.DUPLICATE_REQUEST, MsgContants.DUPLICATE_REQUEST);
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(JSONUtil.toJSONString(responseData));
					return false;
				}
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
		Request requestData = (Request) request.getAttribute(CommonConstant.REQUEST_ATTRIBUTE);
		if (requestData != null) {
			String duplicateKey = requestData.getDuplicateKey();
			if (duplicateKey != null) {
				String requestUri = request.getRequestURI();
				String memcacheKey = requestUri + ":" + duplicateKey;
				memcachedService.memcachedUnlock(memcacheKey);
			}
		}
	}
}
