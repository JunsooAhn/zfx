package com.moneylocker.account.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moneylocker.account.constant.RebindPhoneState;
import com.moneylocker.account.constant.UpdateInfoState;
import com.moneylocker.account.constant.UserInfoType;
import com.moneylocker.account.constant.UserPartInfoType;
import com.moneylocker.account.dao.UserInfoDao;
import com.moneylocker.account.service.AccountService;
import com.moneylocker.common.util.DateUtil;
import com.moneylocker.common.util.EncryptUtil;
import com.moneylocker.common.util.StringUtils;

@Service(value = "accountService")
@Transactional(value = "serviceTxManager")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserInfoDao userInfoDao;


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateUserNick(String userId, String nickName) {
		if(userInfoDao.isExistSingleProperty(UserInfoType.NICKNAME, nickName)){
			return UpdateInfoState.NICK_NAME_EXIST;
		}
		String oldNickName = userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.NICKNAME);
		boolean isEdit = userInfoDao.updateUserInfoSinglePropertyWithCheck(userId, UserInfoType.NICKNAME, nickName,
				oldNickName);
		if (StringUtils.isEmpty(oldNickName) && isEdit == true) {
			return UpdateInfoState.SUCCESS_FIRST_TIME;
		} else if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateBirthday(String userId, String birthday) {
		String oldBirthday = userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.BIRTHDAY);
		boolean isEdit = userInfoDao.updateUserInfoSinglePropertyWithCheck(userId, UserInfoType.BIRTHDAY, birthday,
				oldBirthday);
		if (StringUtils.isEmpty(oldBirthday) && isEdit == true) {
			return UpdateInfoState.SUCCESS_FIRST_TIME;
		} else if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateGender(String userId, Short gender) {
		Object oldGender = userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.GENDER);
		boolean isEdit = userInfoDao.updateUserInfoSinglePropertyWithCheck(userId, UserInfoType.GENDER, gender,
				oldGender);
		if (oldGender == null && isEdit == true) {
			return UpdateInfoState.SUCCESS_FIRST_TIME;
		} else if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateViewWebsite(String userId) {
		Object oldViewHspInfoFlg = userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.VIEW_WEBSITE);
		boolean isEdit = userInfoDao.updateUserInfoSinglePropertyWithCheck(userId, UserInfoType.VIEW_WEBSITE, 1,
				oldViewHspInfoFlg);
		if (oldViewHspInfoFlg == null && isEdit == true) {
			return UpdateInfoState.SUCCESS_FIRST_TIME;
		} else if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkPassword(String userId, String password) {
		Map<String, Object> passwordInfo = userInfoDao.getPartInfoByUserId(userId, UserPartInfoType.PASSWORD);
		if (!EncryptUtil.sha(password + passwordInfo.get("regTime")).equals(passwordInfo.get("password"))) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkPhoneExist(String phone) {
		return userInfoDao.isExistSingleProperty(UserInfoType.PHONE, phone);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkUserPhone(String userId, String phone) {
		if (!phone.equals(userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.PHONE))) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean IsChangedPhone(String userId) {
		Long lastUpdatePhoneTime = Long.valueOf((String) userInfoDao.getUserInfoSingleProperty(userId,
				UserInfoType.UPDATE_PHONE_TIME));
		Long oneMonthTime = System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000;
		if (lastUpdatePhoneTime <= oneMonthTime) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkImeiExist(String imei) {
		if (userInfoDao.isExistSingleProperty(UserInfoType.IMEI, imei)) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkImei(String userId, String imei) {
		if (imei.equals(userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.IMEI))) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int bindPhone(String userId, String phone, String password, Short verType) {
		String regTime = userInfoDao.getUserInfoSingleProperty(userId, UserInfoType.REGTIME);
		String updatePhoneTime = System.currentTimeMillis() + "";
		boolean isEdit = userInfoDao.bindPhone(userId, phone, EncryptUtil.sha(password + regTime), updatePhoneTime,
				verType);
		if (isEdit == true) {
			return UpdateInfoState.SUCCESS_FIRST_TIME;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int rebindPhone(String userId, String phone, String oldPhone, String password, Short verType,
			int allowRebindAfterDays) {
		Map<String, Object> passwordInfo = userInfoDao.getPartInfoByUserId(userId, UserPartInfoType.REBIND_PHONE);
		if (passwordInfo == null) {
			return RebindPhoneState.USER_NOT_EXIST;
		}
		String currentPhone = (String) passwordInfo.get("phone");
		String regTime = (String) passwordInfo.get("regTime");
		String currentPassword = (String) passwordInfo.get("password");
		String updateTime = (String) passwordInfo.get("updatePhoneTime");

		if (!oldPhone.equals(currentPhone)) {
			return RebindPhoneState.OLD_PHONE_ERROR;
		}

		if (!currentPassword.equals(EncryptUtil.sha(password + regTime))) {
			return RebindPhoneState.PASSWORD_WRONG;
		}

		if (!StringUtils.isEmpty(updateTime)) {
			Long lastUpdateTime = Long.valueOf(updateTime);
			Long now = new Date().getTime();
			if (now - lastUpdateTime <= DateUtil.dayToMilliSeconds(allowRebindAfterDays)) {
				return RebindPhoneState.BIND_TOO_FRENQUENTLY;
			}
		}
		String updatePhoneTime = System.currentTimeMillis() + "";
		boolean isEdit = userInfoDao.rebindPhone(userId, currentPhone, oldPhone, updatePhoneTime, verType);
		if (isEdit == true) {
			return RebindPhoneState.SUCCESS;
		} else {
			return RebindPhoneState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updateBaiduId(String userId, String baiduId, String baiduChannelId) {
		// TODO 是不是要生成baidu group
		String baiduGroup = null;
		boolean isEdit = userInfoDao.updateBaiduId(userId, baiduId, baiduChannelId, baiduGroup);
		if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int updatePassword(String userId, String newPassword, String oldPassword) {
		Map<String, Object> passwordInfo = userInfoDao.getPartInfoByUserId(userId, UserPartInfoType.PASSWORD);
		if (!EncryptUtil.sha(oldPassword + passwordInfo.get("regTime")).equals(passwordInfo.get("password"))) {
			return UpdateInfoState.PASSWORD_WRONG;
		}
		String newPasswordSha = EncryptUtil.sha(newPassword + passwordInfo.get("regTime"));
		boolean isEdit = userInfoDao.updateUserInfoSingleProperty(userId, UserInfoType.PASSWORD, newPasswordSha);
		if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int findBackPassword(String imei, String phone, String password) {
		Map<String, Object> passwordInfo = userInfoDao.getPartInfoByPhone(phone, UserPartInfoType.PASSWORD);
		if (passwordInfo == null) {
			return UpdateInfoState.USER_NOT_EXIST;
		}
		String userId = (String)passwordInfo.get("userId");
		String userRegtime = (String)passwordInfo.get("userRegtime");
		boolean isEdit = userInfoDao
				.updateUserInfoSingleProperty(userId, UserInfoType.PASSWORD, EncryptUtil.sha(password + userRegtime));
		if (isEdit == true) {
			return UpdateInfoState.SUCCESS;
		} else {
			return UpdateInfoState.FAILED;
		}
	}

}
