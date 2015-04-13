package com.zfx.consumer.handler;

import java.util.Calendar;

import kafka.message.MessageAndMetadata;

import com.zfx.account.bean.ImeiPhoneLog;
import com.zfx.account.bean.UserInfo;
import com.zfx.account.bean.UserLoginLog;
import com.zfx.account.constant.LoginResultState;
import com.zfx.account.constant.UserCheatState;
import com.zfx.common.util.DateUtil;
import com.zfx.common.util.JSONUtil;
import com.zfx.consumer.dao.ImeiPhoneDao;
import com.zfx.consumer.dao.UserCheatDao;
import com.zfx.consumer.dao.UserDao;

public class PostLoginProcessMessageHandler implements MessageHandler {

	private ImeiPhoneDao imeiPhoneDao;

	private UserDao userDao;

	private UserCheatDao userCheatDao;

	public void setImeiPhoneDao(ImeiPhoneDao imeiPhoneDao) {
		this.imeiPhoneDao = imeiPhoneDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserCheatDao(UserCheatDao userCheatDao) {
		this.userCheatDao = userCheatDao;
	}

	@Override
	public void onMessage(MessageAndMetadata<byte[], byte[]> msgData, int m_threadNumber, String topic) {


		UserLoginLog loginLog = JSONUtil.parseObject(new String(msgData.message()), UserLoginLog.class);
		UserInfo userInfo = userDao.getUserInfoById(loginLog.getUserId());
		if (userInfo != null) {
			boolean isUnTrustUser = userInfo.getIsWhite() == null || userInfo.getIsWhite() == 0;
			if (isUnTrustUser
					&& isChangeAccoutFrequentlyOnSameDevice(loginLog.getImei(), loginLog.getAndroidId(),
							loginLog.getMacAddress(), userInfo.getPhone())) {
				userInfo.setUserCheatState((short) UserCheatState.ACCOUNT_CHANGE_FREQUENTLY_CHEAT);
				loginLog.setLoginResultState(LoginResultState.ACCOUNT_CHANGE_FREQUENTLY);
			}
			if (loginLog.getLoginResultState() == LoginResultState.ACCOUNT_CHANGE_FREQUENTLY) {
				userCheatDao.addUserLoginToCheatList(loginLog);
			}
		}
	}
	

	private boolean isChangeAccoutFrequentlyOnSameDevice(String userImei, String androidId, String macAddress,
			String userPhone) {
		ImeiPhoneLog imeiPhoneLog = imeiPhoneDao.getImeiPhoneLog(userImei, androidId, macAddress);
		int today = DateUtil.toDayInt(Calendar.getInstance());
		if (imeiPhoneLog == null) {
			imeiPhoneLog = new ImeiPhoneLog(userImei, androidId, macAddress, userPhone, Short.valueOf("0"), today);
			imeiPhoneDao.saveImeiPhoneLog(imeiPhoneLog);
			return false;
		}

		if (imeiPhoneLog.getDay() != today) {
			imeiPhoneDao.updateNewDayImeiPhoneLog(userImei, androidId, macAddress, userPhone, today);
			return false;
		}

		if (imeiPhoneLog.getPhone().equals(userPhone)) {
			return false;
		} else {
			if (imeiPhoneLog.getCount() == 0) {
				imeiPhoneDao.updateImeiPhoneLogCount(userImei, androidId, macAddress, userPhone);
				return false;
			}
		}

		return true;
	}

}
