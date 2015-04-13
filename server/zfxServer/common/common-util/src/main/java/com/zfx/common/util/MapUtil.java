package com.zfx.common.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	public static Map<Object, Object> build(Object... keyValues) {
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		for (int i = 0; i < keyValues.length; i += 2) {
			resultMap.put(keyValues[i], keyValues[i + 1]);
		}
		return resultMap;
	}
}
