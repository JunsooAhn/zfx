package com.moneylocker.account.mq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moneylocker.account.bean.UserLoginLog;
import com.moneylocker.common.util.KafkaProducer;

public class KafkaProducerTest {

	private ClassPathXmlApplicationContext classPathXmlApplicationContext;

	@Before
	public void init() {
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext("common-spring-context-kafka-producer.xml");
	}

	public static List<UserLoginLog> generateLoginTestData() {

		List<UserLoginLog> loginResults = new ArrayList<UserLoginLog>();

		for (int i = 0; i < 30; i++) {
			UserLoginLog result = new UserLoginLog();

			result.setLoginId(String.valueOf(i));
			result.setUserId("0000000044cafb830144cb06b52c2a17");
			result.setAndroidId(String.valueOf(i));
			result.setImei("imei" + String.valueOf(i));
			result.setAppVersion(i);
			result.setManufacturer("Xiaomi" + i);
			result.setModel("CNMA" + i);
			result.setMacAddress("Mac" + i);

			loginResults.add(result);
		}
		return loginResults;
	}

	public static List<UserLoginLog> generateLoginInfo() {
		List<UserLoginLog> loginInfos = new ArrayList<UserLoginLog>();
		for (int i = 0; i < 30; i++) {
			UserLoginLog loginLog = new UserLoginLog();
			loginLog.setUserId(String.valueOf(i));
			loginLog.setDate(new Date());
			loginLog.setLoginId(String.valueOf(i));
			loginInfos.add(loginLog);
		}
		return loginInfos;
	}

	@Test
	public void test() {

		KafkaProducer kafkaProducer = classPathXmlApplicationContext.getBean("kafkaProducerASync", KafkaProducer.class);
		if (kafkaProducer != null) {
			List<UserLoginLog> loginResults = generateLoginTestData();

			List<UserLoginLog> loginInfos = generateLoginInfo();

			for (UserLoginLog loginResult : loginResults) {
				kafkaProducer.send("post_login_process", null, loginResult);
				System.out.println("sending information");
			}

			for (UserLoginLog loginInfo : loginInfos) {
				kafkaProducer.send("ml_login", null, loginInfo);
			}
		}
	}

	@After
	public void destroy() {
		classPathXmlApplicationContext.close();
	}
}
