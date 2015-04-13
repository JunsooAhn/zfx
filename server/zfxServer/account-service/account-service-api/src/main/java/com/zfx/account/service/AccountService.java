package com.zfx.account.service;



public interface AccountService {
	
	public int updateUserNick(String userId, String nickName);
	
	public int updateBirthday(String userId, String birthday);
	
	public int updateGender(String userId, Short gender);
	
	public int updateViewWebsite(String userId);
	
	public int bindPhone(String userId, String phone, String password, Short verType);
	
	public int rebindPhone(String userId, String phone, String oldPhone, String password, Short verType,
			int allowRebindAfterDays);

	public int updateBaiduId(String userId, String baiduId, String userBaiduChannelId);
	
	public boolean checkPassword(String userId, String password);
	
	public boolean checkPhoneExist(String phone);
	
	public int updatePassword(String userId, String password, String oldPassword);
	
	public boolean IsChangedPhone(String userId);
	
	public boolean checkUserPhone(String userId, String phone);
	
	public boolean checkImei(String userId, String imei);
	
	public boolean checkImeiExist(String imei);

	public int findBackPassword(String imei, String phone, String password);

}
