package com.moneylocker.common.util;

import java.util.regex.Pattern;

public class StringUtils {

	private static final Pattern phonePattern = Pattern.compile("^((1[0-9]))\\d{9}$");;

	public static boolean isEmpty(String input) {
		return org.springframework.util.StringUtils.isEmpty(input);
	}

	public static boolean isValidPhone(String phone) {
		if (isEmpty(phone) || !phonePattern.matcher(phone).matches()) {
			return false;
		}
		return true;
	}

	public static boolean isValidImei(String imei) {
		if (isEmpty(imei) || imei.length() < 5) {
			return false;
		}
		return true;
	}
}
