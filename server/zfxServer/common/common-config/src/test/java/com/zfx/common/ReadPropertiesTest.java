package com.zfx.common;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zfx.common.config.DynamicPropertiesHelper;
import com.zfx.common.config.DynamicPropertiesHelperFactory;

public class ReadPropertiesTest extends TestCase {
	private DynamicPropertiesHelperFactory helperFactory;

	protected void setUp() throws Exception {
		super.setUp();
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-spring-config.xml");
		this.helperFactory = ((DynamicPropertiesHelperFactory) ctx
				.getBean(DynamicPropertiesHelperFactory.class));
	}

	public void testReadProperties() throws InterruptedException {
		DynamicPropertiesHelper helper = this.helperFactory
				.getHelper("a.properties");
		helper.registerHandler(ConfigABean.getInstance());
		while (true) {
			//System.out.println(helper.getProperty("a"));
			System.out.println(ConfigABean.getInstance().getA());
			System.out.println(ConfigABean.getInstance().isB());
			System.out.println(ConfigABean.getInstance().getC());
			Thread.sleep(5000L);
		}

	}
}
