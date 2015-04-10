package com.moneylocker.frontend.account.bean.request;

import com.moneylocker.common.util.StringUtils;
import com.moneylocker.frontend.account.constant.MsgContants;
import com.moneylocker.frontend.common.bean.Request;
import com.moneylocker.frontend.common.bean.Response;

public class RecommendAddRequest extends Request {

	private String referenceCode;

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (StringUtils.isEmpty(referenceCode)) {
			return Response.build(Response.PARAM_INVALID, MsgContants.RECOMMEND_REFERENCE_CODE_EMPTY);
		}
		return null;
	}
}
