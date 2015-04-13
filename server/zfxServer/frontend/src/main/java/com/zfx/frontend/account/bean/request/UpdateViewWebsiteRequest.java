package com.zfx.frontend.account.bean.request;

import com.zfx.frontend.account.constant.MsgContants;
import com.zfx.frontend.common.bean.Request;
import com.zfx.frontend.common.bean.Response;

public class UpdateViewWebsiteRequest extends Request {

	public short viewHspInfoFlg;

	public short getViewHspInfoFlg() {
		return viewHspInfoFlg;
	}

	public void setViewHspInfoFlg(short viewHspInfoFlg) {
		this.viewHspInfoFlg = viewHspInfoFlg;
	}

	@Override
	public Response validate() {
		Response result = super.validate();
		if (result != null) {
			return result;
		}

		if (viewHspInfoFlg > 1 || viewHspInfoFlg < 0) {
			return Response.build(Response.PARAM_INVALID, MsgContants.VIEW_WEBSITE_FAILD);
		}
		return super.validate();
	}
}
