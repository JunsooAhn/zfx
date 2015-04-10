package com.moneylocker.common.util;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaProducerTest {

	private ClassPathXmlApplicationContext classPathXmlApplicationContext;

	@Before
	public void init() {
		classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"common-spring-context-kafka-producer.xml");
	}
	
	@After
	public void destroy() {
		classPathXmlApplicationContext.close();
	}
}
