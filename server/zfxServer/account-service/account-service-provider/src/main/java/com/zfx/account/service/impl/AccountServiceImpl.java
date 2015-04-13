package com.zfx.account.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zfx.account.constant.RebindPhoneState;
import com.zfx.account.constant.UpdateInfoState;
import com.zfx.account.constant.UserInfoType;
import com.zfx.account.constant.UserPartInfoType;
import com.zfx.account.dao.UserInfoDao;
import com.zfx.account.service.AccountService;
import com.zfx.common.util.DateUtil;
import com.zfx.common.util.EncryptUtil;
import com.zfx.common.util.StringUtils;

@Service(value = "accountService")
@Transactional(value = "serviceTxManager")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateUserNick(String userId, String nickName) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateBirthday(String userId, String birthday) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateGender(String userId, Short gender) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateViewWebsite(String userId) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkPassword(String userId, String password) {
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkPhoneExist(String phone) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkUserPhone(String userId, String phone) {
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean IsChangedPhone(String userId) {
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkImeiExist(String imei) {
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkImei(String userId, String imei) {
		return false;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int bindPhone(String userId, String phone, String password,
			Short verType) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int rebindPhone(String userId, String phone, String oldPhone,
			String password, Short verType, int allowRebindAfterDays) {
		return RebindPhoneState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updatePassword(String userId, String newPassword,
			String oldPassword) {
		return UpdateInfoState.FAILED;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int findBackPassword(String imei, String phone, String password) {
		return UpdateInfoState.FAILED;
	}

}
