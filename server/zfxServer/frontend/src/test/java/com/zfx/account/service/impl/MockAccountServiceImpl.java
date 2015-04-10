package com.moneylocker.account.service.impl;

import org.springframework.stereotype.Service;

import com.moneylocker.account.service.AccountService;

@Service
public class MockAccountServiceImpl implements AccountService {

	@Override
	public int updateUserNick(String userId, String nickName) {
		return 0;
	}

	@Override
	public int updateBirthday(String userId, String birthday) {
		return 0;
	}

	@Override
	public int updateGender(String userId, Short gender) {
		return 0;
	}

	@Override
	public int updateViewWebsite(String userId) {
		return 0;
	}

	@Override
	public int updateBaiduId(String userId, String baiduId, String userBaiduChannelId) {
		return 0;
	}

	@Override
	public boolean checkPassword(String userId, String password) {
		return false;
	}

	@Override
	public boolean checkPhoneExist(String phone) {
		return false;
	}

	@Override
	public int updatePassword(String userId, String password, String oldPassword) {
		return 0;
	}

	@Override
	public boolean IsChangedPhone(String userId) {
		return false;
	}

	@Override
	public boolean checkUserPhone(String userId, String phone) {
		return false;
	}

	@Override
	public boolean checkImei(String userId, String imei) {
		return false;
	}

	@Override
	public boolean checkImeiExist(String imei) {
		return false;
	}

	@Override
	public int findBackPassword(String imei, String phone, String password) {
		return 0;
	}

	@Override
	public int bindPhone(String userId, String phone, String password, Short verType) {
		return 0;
	}


	@Override
	public int rebindPhone(String userId, String phone, String oldPhone, String password, Short verType,
			int allowRebindAfterDays) {
		return 0;
	}


}
