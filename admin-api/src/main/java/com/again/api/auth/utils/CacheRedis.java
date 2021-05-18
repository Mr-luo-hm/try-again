package com.again.api.auth.utils;

import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class CacheRedis {

	private final StringRedisTemplate redisTemplate;

	/**
	 * 普通缓存获取
	 * @param key 键
	 * @return 值
	 */
	public String get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * 普通缓存放入
	 * @param key 键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean set(String key, Object value) {
		try {
			String jsonStr = JacksonUtils.toJson(value);
			redisTemplate.opsForValue().set(key, jsonStr);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * String 类型的操作 如果key 存在，会把value 追加到原来值的结尾 如果不存在 新增再追加
	 * @param key {@code key}
	 * @param value {@code value}
	 * @return 返回append后字符串值（value）的长度。
	 */
	public Integer appEnd(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	/**
	 * 原子操作 对key 对应的数字减{@code value} 不存在会被置为0 减{@code value}
	 * @param key {@code key}
	 * @param value {@code value}
	 * @return 减小之后的value
	 */
	public Long decr(String key, long value) {
		return redisTemplate.opsForValue().decrement(key, value);
	}

	/**
	 * 原子操作 对key 对应的数字减1 不存在会被置为0 减1
	 * @param key {@code key}
	 * @return 减小之后的value
	 */
	public Long decr(String key) {
		return redisTemplate.opsForValue().decrement(key);
	}

	/**
	 * 原子操作 对key 对应的数字加{@code value} 不存在会被置为0 加{@code value}
	 * @param key {@code key}
	 * @return 减小之后的value
	 */
	public Long incr(String key, long value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	/**
	 * 原子操作 对key 对应的数字加1 不存在会被置为0 加1
	 * @param key {@code key}
	 * @return 减小之后的value
	 */
	public Long incr(String key) {
		return redisTemplate.opsForValue().increment(key);
	}

	/**
	 * 原子操作 对key 对应的数字加浮点数
	 * @param key {@code key}
	 * @return
	 */
	public Double incr(String key, double value) {
		return redisTemplate.opsForValue().increment(key, value);
	}

	/**
	 * 获取所有key 对应的value
	 * @param keys key的集合
	 * @return value 集合
	 */
	public List<String> mGet(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	/**
	 * 存放所有的String类型数据
	 * @param map 对应的key value 集合
	 */
	public void multiSet(Map<String, String> map) {
		redisTemplate.opsForValue().multiSet(map);
	}

	/**
	 * 将多个key 存放到redis 只要有一个key已经存在，MSETNX一个操作都不会执行
	 * @param map 对应的key value 集合
	 * @return 成功 失败
	 */
	public Boolean multiSetNx(Map<String, String> map) {
		return redisTemplate.opsForValue().multiSetIfAbsent(map);
	}

}
