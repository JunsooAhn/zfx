package com.moneylocker.frontend.common.processor;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.interceptor.DecryptInterceptor;

public class RequestClassBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().getAnnotation(Controller.class) != null) {
			RequestMapping clsRequestMapping = bean.getClass().getAnnotation(RequestMapping.class);
			if (clsRequestMapping != null) {
				String[] clsMappings = clsRequestMapping.value();
				if (clsMappings.length > 0) {
					String clsMapping = clsMappings[0];
					if (!clsMapping.startsWith("/")) {
						clsMapping = "/" + clsMapping;
					}
					for (Method method : bean.getClass().getMethods()) {
						RequestMapping mtdRequestMapping = method.getAnnotation(RequestMapping.class);
						if (mtdRequestMapping != null) {
							String[] mtdMappings = mtdRequestMapping.value();
							if (mtdMappings.length > 0) {
								String mtdMapping = mtdMappings[0];
								String wholeUri = null;
								if (mtdMapping.startsWith("/")) {
									wholeUri = clsMapping + mtdMapping;
								} else if (!StringUtils.isEmpty(mtdMapping)) {
									wholeUri = clsMapping + "/" + mtdMapping;
								} else {
									wholeUri = clsMapping;
								}
								for (Class<?> paramCls : method.getParameterTypes()) {
									if (Request.class.isAssignableFrom(paramCls)) {
										DecryptInterceptor.register(wholeUri, paramCls);
										break;
									}
								}
							}
						}

					}
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
