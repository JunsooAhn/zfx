package com.zfx.common.service.impl;

import java.util.Map;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfx.common.service.MemcachedService;

@Service(value = "memcachedService")
public class MemcachedServiceImpl implements MemcachedService {

	private static final Logger logger = LoggerFactory.getLogger(MemcachedServiceImpl.class);

	protected MemcachedClient memcachedClient;

	//@Autowired
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public boolean memcachedSet(String memcacheId, int exp, Object target) {
		try {
			memcachedClient.set(memcacheId, exp, target).get();
			return true;
		} catch (Exception e) {
			logger.error(memcacheId, e);
		}
		return false;
	}

	@Override
	public void memcachedDelete(String memcacheId) {
		try {
			memcachedClient.delete(memcacheId);
		} catch (Exception e) {
			logger.error(memcacheId, e);
		}
	}

	@Override
	public boolean memcachedLock(String memcacheId) {
		try {
			Object object = memcachedClient.get(memcacheId);
			if (object != null) {
				return false;
			}
			memcachedClient.set(memcacheId, 40, true);
		} catch (Exception e) {
			logger.error(memcacheId, e);
		}
		return true;
	}

	@Override
	public void memcachedUnlock(String memcacheId) {
		try {
			memcachedClient.delete(memcacheId);
		} catch (Exception e) {
			logger.error(memcacheId, e);
		}
	}

	@Override
	public Object memcachedGet(String memcacheId) {
		try {
			return memcachedClient.get(memcacheId);
		} catch (Exception e) {
			logger.error(memcacheId, e);
		}
		return null;
	}

	@Override
	public Map<String, Object> memcachedBulkGet(String... memcacheIds) {
		try {
			return memcachedClient.getBulk(memcacheIds);
		} catch (Exception e) {
			logger.error(memcacheIds[0], e);
		}
		return null;
	}
}
