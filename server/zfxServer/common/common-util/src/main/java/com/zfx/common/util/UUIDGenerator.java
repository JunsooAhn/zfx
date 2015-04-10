package com.moneylocker.common.util;

import java.security.SecureRandom;

/*
 * 由于java自带的UUID toString会加入横杠，那么长度就是36位，为了兼容hibernate的老数据，所以把java UUID的代码拷贝过来
 */
public class UUIDGenerator {

	public static String generate() {
		SecureRandom ng = new SecureRandom();

		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);
		randomBytes[6] &= 0x0f;
		randomBytes[6] |= 0x40;
		randomBytes[8] &= 0x3f;
		randomBytes[8] |= 0x80;

		long msb = 0;
		long lsb = 0;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (randomBytes[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (randomBytes[i] & 0xff);
		long mostSigBits = msb;
		long leastSigBits = lsb;

		return (digits(mostSigBits >> 32, 8) + digits(mostSigBits >> 16, 4) + digits(mostSigBits, 4)
				+ digits(leastSigBits >> 48, 4) + digits(leastSigBits, 12));
	}

	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}
}
