package com.again.starter.redis.operation;

import com.again.starter.redis.config.CachePropertiesHolder;
import org.aspectj.lang.ProceedingJoinPoint;

public abstract class AbstractCacheOps {

	public AbstractCacheOps(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	private final ProceedingJoinPoint joinPoint;

	/**
	 * 织入方法
	 * @return ProceedingJoinPoint
	 */
	public ProceedingJoinPoint joinPoint() {
		return joinPoint;
	}

	/**
	 * 检查缓存数据是否是空值
	 * @param cacheData
	 * @return
	 */
	public boolean nullValue(Object cacheData) {
		return CachePropertiesHolder.nullValue().equals(cacheData);
	}

}
