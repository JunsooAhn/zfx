package com.zfx.frontend.account.bean.request;

import com.zfx.common.util.StringUtils;
import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

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
