package com.moneylocker.common.util;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

public class LongIdentityGeneratorTest {

	@Test
	public void testUnique() {
		LongIdentityGenerator longIdentityGenerator = new LongIdentityGenerator(91);
		int size = 1000000;
		Set<Long> resultSet = new HashSet<Long>();
		for (int i = 0; i < size; ++i) {
			long num = longIdentityGenerator.nextIdentifier();
			resultSet.add(num);
		}
		Assert.assertTrue(resultSet.size() == size);
	}
}
