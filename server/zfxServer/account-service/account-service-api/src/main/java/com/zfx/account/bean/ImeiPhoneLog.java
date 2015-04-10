package com.moneylocker.account.bean;

public class ImeiPhoneLog {
	private String imei;
	private String phone;
	private String androidId;
	private String macAddress;
	private short count;
	private int day;

	public ImeiPhoneLog() {
	}

	public ImeiPhoneLog(String imei, String androidId, String macAddress, String phone, short count, int day) {
		this.imei = imei;
		this.phone = phone;
		this.count = count;
		this.day = day;
		this.androidId = androidId;
		this.macAddress = macAddress;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public short getCount() {
		return count;
	}

	public void setCount(short count) {
		this.count = count;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
}
