package com.zfx.common.util;

import java.util.Random;

public class RandomUtil {

	public static String seeds = "0123456789";

	public static String randomNum(int length) {
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; ++i) {
			result.append(random.nextInt(10));
		}
		return result.toString();
	}

	public static String randomCharAndNum(int length) {
		StringBuffer result = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; ++i) {
			result.append(seeds.charAt(random.nextInt(seeds.length())));
		}
		return result.toString();
	}
}
