package com.zfx.common.service;

import java.util.Map;


public interface MemcachedService {

	public boolean memcachedSet(String memcacheId, int exp, Object target);

	public void memcachedDelete(String memcacheId);
	
	public boolean memcachedLock(String memcacheId);
	
	public void memcachedUnlock(String memcacheId);
	
	public Object memcachedGet(String memcacheId);
	
	public Map<String, Object> memcachedBulkGet(String... memcacheIds);
}
