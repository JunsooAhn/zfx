package com.moneylocker.consumer.handler;

import kafka.message.MessageAndMetadata;

import com.moneylocker.account.bean.ImeiPhoneLog;
import com.moneylocker.common.util.JSONUtil;
import com.moneylocker.consumer.dao.ImeiPhoneDao;

public class SaveImeiMessageHandler implements MessageHandler {

	private ImeiPhoneDao imeiPhoneDao;


	public void setImeiPhoneDao(ImeiPhoneDao imeiPhoneDao) {
		this.imeiPhoneDao = imeiPhoneDao;
	}


	@Override
	public void onMessage(MessageAndMetadata<byte[], byte[]> msgData, int m_threadNumber, String topic) {

		ImeiPhoneLog imeiPhoneLog = JSONUtil.parseObject(new String(msgData.message()), ImeiPhoneLog.class);
		imeiPhoneDao.saveImeiPhoneLog(imeiPhoneLog);
		System.out.println("Imei information saved " + imeiPhoneLog.getImei());

	}
	



}
