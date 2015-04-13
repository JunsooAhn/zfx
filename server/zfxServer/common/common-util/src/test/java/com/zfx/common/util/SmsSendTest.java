package com.zfx.common.util;

import org.junit.Test;

import com.zfx.common.util.verify.VerifyInterface;
import com.zfx.common.util.verify.impl.eucp.EucpMessageVerifyImpl;

public class SmsSendTest {

	@Test
	public void sendSms() {
		VerifyInterface messageVerify = new EucpMessageVerifyImpl();
		messageVerify.sendVerifyInformation("15921564975", "123456");
	}
}
