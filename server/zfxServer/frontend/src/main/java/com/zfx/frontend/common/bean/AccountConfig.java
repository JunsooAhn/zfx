package com.moneylocker.frontend.common.bean;

import com.moneylocker.common.config.BasePropertyConfig;

public class AccountConfig extends BasePropertyConfig {

	private int allowRebindAfterDays = 30;

	public int getAllowRebindAfterDays() {
		return allowRebindAfterDays;
	}

	public void setAllowRebindAfterDays(int allowRebindAfterDays) {
		this.allowRebindAfterDays = allowRebindAfterDays;
	}

}
