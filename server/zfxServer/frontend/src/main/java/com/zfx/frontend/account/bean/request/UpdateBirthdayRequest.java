package com.moneylocker.frontend.account.bean.request;

import java.util.Calendar;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class UpdateBirthdayRequest extends Request {

	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (StringUtils.isEmpty(birthday)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.BIRTHDAY_EMPTY);
		}

		if (birthday.length() < 5) {
			return Response.build(Response.PARAM_INVALID, MsgContants.BIRTHDAY_UPDATE_FAILD);
		}

		if (birthday.length() >= 5) {
			String birthYear = birthday.substring(0, 4);
			String birthMonth = birthday.substring(5, birthday.length());
			Calendar cal = Calendar.getInstance();
			if (Integer.parseInt(birthYear) > cal.get(Calendar.YEAR) || Integer.parseInt(birthYear) < 1888) {
				return Response.build(Response.PARAM_INVALID, MsgContants.BIRTHDAY_YEAR_FAILD);
			}

			if (Integer.parseInt(birthMonth) > 12 || Integer.parseInt(birthMonth) < 1) {
				return Response.build(Response.PARAM_INVALID, MsgContants.BIRTHDAY_MONTH_FAILD);
			}
		}
		return null;
	}
}
