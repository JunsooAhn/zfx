package com.moneylocker.consumer.dao;

import com.moneylocker.account.bean.ImeiPhoneLog;


public interface ImeiPhoneDao {

	public ImeiPhoneLog getImeiPhoneLog(String imei, String androidId, String macAddress);
	
	public boolean saveImeiPhoneLog(ImeiPhoneLog imeiPhoneLog);
	
	public boolean updateNewDayImeiPhoneLog(String imei, String androidId, String macAddress, String phone, int day);
	
	public boolean updateImeiPhoneLogCount(String imei, String androidId, String macAddress, String phone);
}
