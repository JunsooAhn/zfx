package com.moneylocker.common.util;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	public static <T> T parseObject(String text, Class<T> cls) {
		return JSON.parseObject(text, cls);
	}
}
