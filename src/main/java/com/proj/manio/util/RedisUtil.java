package com.proj.manio.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 存入 key-value
    public  void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 取出 key 对应的值
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除 key
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}