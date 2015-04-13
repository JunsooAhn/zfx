package com.zfx.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zfx.account.bean.RecommendInfo;
import com.zfx.account.dao.RecommendLogDao;
import com.zfx.common.dao.impl.BaseServiceDaoImpl;
import com.zfx.common.util.MapUtil;
import com.zfx.common.util.UUIDGenerator;

@Repository
public class RecommendDaoLogImpl extends BaseServiceDaoImpl implements RecommendLogDao {

	private final static String NAMESPACE_RECOMMEND_LOG = "com.zfx.account.bean.RecommendLog";

	@Override
	public void addRecommendLog(String rUserId, String rPhone, String rRecommendCode, String rdUserId, int credit) {
		String nowTime = System.currentTimeMillis() + "";
		String id = UUIDGenerator.generate();
		getSqlSession().insert(
				NAMESPACE_RECOMMEND_LOG + ".addRecommendLog",
				MapUtil.build("id", id, "rUserId", rUserId, "rPhone", rPhone, "rRecommendCode", rRecommendCode,
						"rdUserId", rdUserId, "logTime", nowTime, "credit", credit));
	}

	@Override
	public int getRecommendSummary(String userId) {
		return getSqlSession().selectOne(NAMESPACE_RECOMMEND_LOG + ".getRecommendCount",
				MapUtil.build("userId", userId));
	}

	@Override
	public List<RecommendInfo> getRecommendDetail(String userId) {
		return getSqlSession().selectList(NAMESPACE_RECOMMEND_LOG + ".getRecommendDetail",
				MapUtil.build("userId", userId));
	}
}
