package com.moneylocker.common.util;


import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class UUIDGeneratorTest {

	@Test
	public void testLength32() {
		String id = UUIDGenerator.generate();
		Assert.assertTrue(id.length() == 32);
	}

	@Test
	public void testUnique() {
		int size = 10000;
		Set<String> resultSet = new HashSet<String>();
		for (int i = 0; i < size; ++i) {
			resultSet.add(UUIDGenerator.generate());
		}
		Assert.assertTrue(resultSet.size() == size);
	}

	@Test
	public void testLowCase() {
		String id = UUIDGenerator.generate();
		Assert.assertTrue(id.equals(id.toLowerCase()));
	}
}
