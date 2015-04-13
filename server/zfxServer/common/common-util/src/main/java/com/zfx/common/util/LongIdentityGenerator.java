package com.zfx.common.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class LongIdentityGenerator {

	private long suffix;

	private long lastTime = System.currentTimeMillis() * timeOffset;

	private long offset = 0;

	private AtomicBoolean mutex = new AtomicBoolean(false);

	public LongIdentityGenerator(int idenitifer) {
		if (idenitifer < 0 || idenitifer > 91) {
			throw new IllegalArgumentException("Identifier should be between 0 and 91");
		}
		this.suffix = idenitifer;
	}

	public long nextIdentifier() {
		while (!mutex.compareAndSet(false, true)) {
		}
		try {
			long currentTime = System.currentTimeMillis() * timeOffset;
			if (currentTime > lastTime) {
				lastTime = currentTime;
				offset = offsetStart;
			} else {
				offset = offset + offsetInterval;
				if (offset >= offsetEnd) {
					while (currentTime <= lastTime) {
						currentTime = System.currentTimeMillis() * timeOffset;
					}
				}
			}
			return lastTime + offset + suffix;
		} finally {
			mutex.set(false);
		}
	}

	private static final long timeOffset = 1000000L;

	private static final long offsetStart = 100L;

	private static final long offsetInterval = 100L;

	private static final long offsetEnd = 999900L;
}
