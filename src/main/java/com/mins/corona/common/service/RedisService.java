package com.mins.corona.common.service;

public interface RedisService {

    void put(String key, Object value);

    <T> T get(String key, Class<T> clz);
}
