package com.zfx.frontend.common.bean;

import com.zfx.common.config.BasePropertyConfig;

public class AccountConfig extends BasePropertyConfig {

	private int allowRebindAfterDays = 30;

	public int getAllowRebindAfterDays() {
		return allowRebindAfterDays;
	}

	public void setAllowRebindAfterDays(int allowRebindAfterDays) {
		this.allowRebindAfterDays = allowRebindAfterDays;
	}

}
