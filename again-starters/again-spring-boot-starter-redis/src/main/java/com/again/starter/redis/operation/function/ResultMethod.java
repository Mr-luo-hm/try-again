package com.again.starter.redis.operation.function;

@FunctionalInterface
public interface ResultMethod<T> {

	/**
	 * 执行并返回一个结果
	 * @return
	 */
	T run();

}
