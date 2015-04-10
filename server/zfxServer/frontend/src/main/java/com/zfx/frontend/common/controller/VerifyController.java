package com.moneylocker.frontend.common.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moneylocker.account.constant.UserVerifyType;
import com.moneylocker.common.bean.VerifyLog;
import com.moneylocker.common.constant.CommonConstants;
import com.moneylocker.common.constant.KafkaMessagesConst;
import com.moneylocker.common.service.MemcachedService;
import com.moneylocker.common.util.KafkaProducer;
import com.moneylocker.common.util.RandomUtil;
import com.moneylocker.common.util.verify.VerifyInterface;
import com.moneylocker.frontend.common.bean.Response;
import com.moneylocker.frontend.common.bean.VerifyInfo;
import com.moneylocker.frontend.common.bean.VerifyRequest;
import com.moneylocker.frontend.common.constant.CommonConstant;
import com.moneylocker.frontend.common.constant.MemcacheKeyConstants;
import com.moneylocker.frontend.common.constant.MsgContants;

@Controller
@RequestMapping("verify")
public class VerifyController extends BaseController {

	@Autowired
	@Qualifier("smsVerifier")
	private VerifyInterface messageVerify;

	@Autowired
	@Qualifier("voiceVerifier")
	private VerifyInterface voiceVerify;

	@Autowired
	private MemcachedService memcachedService;

	@Autowired
	@Qualifier("kafkaProducerASync")
	private KafkaProducer kafkaProducer;

	private void sendLogToMessageQueque(VerifyRequest verifyRequest, int verfiyType, int status) {
		VerifyLog verifyLog = new VerifyLog(verifyRequest.getPhone(), verfiyType, status, new Date().getTime(),
				verifyRequest.getVerifyReason());
		kafkaProducer.send(KafkaMessagesConst.VERIFY_LOG_MESSAGE, null, verifyLog);
	}
	@RequestMapping(value = "/sms/send")
	@ResponseBody
	public Response smsSend(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) VerifyRequest verifyRequest) {
		String code = RandomUtil.randomNum(6);
		Response response = null;
		if (messageVerify.sendVerifyInformation(verifyRequest.getPhone(), "【惠锁屏】您的验证码为：" + code)) {
			String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + verifyRequest.getPhone();
			if (memcachedService.memcachedSet(memcacheKey, CommonConstants.FIVE_MINUTES, new VerifyInfo(code,
					UserVerifyType.MESSAGE))) {
				response = Response.build(Response.SUCCESS, MsgContants.SMS_SEND_SUCCESS);
			} else {
				response = Response.build(Response.FAILED, MsgContants.SMS_SEND_FAILED);
			}
			sendLogToMessageQueque(verifyRequest, UserVerifyType.MESSAGE, Response.SUCCESS);
		} else {
			response = Response.build(Response.FAILED, MsgContants.SMS_SEND_FAILED);
			sendLogToMessageQueque(verifyRequest, UserVerifyType.MESSAGE, Response.FAILED);
		}

		return response;
	}

	@RequestMapping(value = "/voice/send")
	@ResponseBody
	public Response voiceSend(@ModelAttribute(CommonConstant.MODEL_ATTRIBUTE) VerifyRequest verifyRequest) {
		String code = RandomUtil.randomNum(6);
		Response response = null;
		if (voiceVerify.sendVerifyInformation(verifyRequest.getPhone(), code)) {
			String memcacheKey = MemcacheKeyConstants.VERFY_CODE_PREFIX + verifyRequest.getPhone();
			if (memcachedService.memcachedSet(memcacheKey, CommonConstants.FIVE_MINUTES, new VerifyInfo(code,
					UserVerifyType.VOICE))) {
				response = Response.build(Response.SUCCESS, MsgContants.VOICE_SEND_SUCCESS);
			} else {
				response = Response.build(Response.FAILED, MsgContants.VOICE_SEND_FAILED);
			}
			sendLogToMessageQueque(verifyRequest, UserVerifyType.VOICE, Response.SUCCESS);
		} else {
			response = Response.build(Response.FAILED, MsgContants.VOICE_SEND_FAILED);
			sendLogToMessageQueque(verifyRequest, UserVerifyType.VOICE, Response.FAILED);
		}
		return response;
	}
}
