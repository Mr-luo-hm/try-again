package com.again.api.auth.utils;

import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * set类型的redis 操作
 */
@Component
@Data
public class CacheRedisSet {
    private final StringRedisTemplate redisTemplate;

    /**
     * 存入一个或者多个元素
     * @param key 键
     * @return 值
     */
    public Long get(String key,String... values) {
        return redisTemplate.opsForSet().add(key,values);
    }
    /**
     * 比较两个几个key 的集合差
     * @param keys 键
     * @return 集合差
     */
    public Set<String> diff(List<String> keys) {
        return redisTemplate.opsForSet().difference(keys);
    }

    /**
     * 获取两个key 并放入新的key中
     * @param key 第一个key
     * @param otherKey 第二个key
     * @param destKey 新key
     * @return 新key 数据长度
     */
    public Long diffAndStore(String key,String otherKey,String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key,otherKey,destKey);
    }

    public Long diffAndStore(String key,List<String> otherKey,String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(key,otherKey,destKey);
    }
    public Long diffAndStore(List<String> otherKey,String destKey) {
        return redisTemplate.opsForSet().differenceAndStore(otherKey,destKey);
    }

}
