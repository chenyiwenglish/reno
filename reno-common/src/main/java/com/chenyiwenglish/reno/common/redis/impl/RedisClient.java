package com.chenyiwenglish.reno.common.redis.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    public void set(String key, String value, Integer timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void setObject(String key, Object object, Integer timeout) {
        redisTemplate.opsForValue().set(key, JSON.toJSONString(object), timeout, TimeUnit.SECONDS);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        return JSON.parseObject((String) redisTemplate.opsForValue().get(key), clazz);
    }

    public <T> T getObject(String key, Type type) {
        return JSON.parseObject((String) redisTemplate.opsForValue().get(key), type);
    }
}
