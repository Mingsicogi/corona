package com.mins.corona.common.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mins.corona.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static ValueOperations<String, Object> valueOperations;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void put(String key, Object value) {
        valueOperations.set(key, value);
    }

    @Override
    public <T> T get(String key, Class<T> clz) {
        return objectMapper.convertValue(valueOperations.get(key), clz);
    }
}
