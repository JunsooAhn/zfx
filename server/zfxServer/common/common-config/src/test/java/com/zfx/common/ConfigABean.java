package com.zfx.common;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.zfx.common.config.DynamicPropertiesHelper.PropertyConvertHandler;

public class ConfigABean implements PropertyConvertHandler{
	private ConfigABean() {
		
	}
	
	private static ConfigABean instance = new ConfigABean();
	private int a;
	private boolean b;
	private long c;

	public int getA() {
		return a;
	}

	public boolean isB() {
		return b;
	}

	public long getC() {
		return c;
	}
	
	public static ConfigABean getInstance() {
		return instance;
	}

	@Override
	public void convert(String key, String value) {
		try {
			BeanUtils.setProperty(this, key, value);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public void setC(long c) {
		this.c = c;
	}


	
	

}
