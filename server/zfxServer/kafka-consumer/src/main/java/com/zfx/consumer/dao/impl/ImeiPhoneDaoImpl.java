package com.zfx.consumer.dao.impl;

import org.springframework.stereotype.Repository;

import com.zfx.account.bean.ImeiPhoneLog;
import com.zfx.common.dao.impl.BaseServiceDaoImpl;
import com.zfx.common.util.MapUtil;
import com.zfx.consumer.dao.ImeiPhoneDao;

@Repository
public class ImeiPhoneDaoImpl extends BaseServiceDaoImpl implements ImeiPhoneDao {

	private final static String NAMESPACE = "com.zfx.account.bean.ImeiPhoneLog";

	@Override
	public ImeiPhoneLog getImeiPhoneLog(String imei, String androidId, String macAddress) {
		return getSqlSession().selectOne(NAMESPACE + ".getImeiPhoneLog",
				MapUtil.build("imei", imei, "androidId", androidId, "macAddress", macAddress));
	}

	@Override
	public boolean saveImeiPhoneLog(ImeiPhoneLog imeiPhoneLog) {
		int insert = getSqlSession().insert(
				NAMESPACE + ".saveImeiPhoneLog",
				MapUtil.build("imei", imeiPhoneLog.getImei(), "phone", imeiPhoneLog.getPhone(), "day",
						imeiPhoneLog.getDay(), "androidId", imeiPhoneLog.getAndroidId(), "macAddress",
						imeiPhoneLog.getMacAddress()));
		return insert > 0;
	}

	@Override
	public boolean updateNewDayImeiPhoneLog(String imei, String androidId, String macAddress, String phone, int day) {
		int update = getSqlSession().update(
				NAMESPACE + ".updateNewDayImeiPhoneLog",
				MapUtil.build("imei", imei, "phone", phone, "day", day, "macAddress", macAddress, "androidId",
						androidId));
		return update > 0;
	}

	@Override
	public boolean updateImeiPhoneLogCount(String imei, String androidId, String macAddress, String phone) {
		int update = getSqlSession().update(NAMESPACE + ".updateImeiPhoneLogCount",
				MapUtil.build("imei", imei, "phone", phone, "androidId", androidId, "macAddress", macAddress));
		return update > 0;
	}

}
