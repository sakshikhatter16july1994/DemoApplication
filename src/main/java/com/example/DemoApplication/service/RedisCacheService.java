package com.example.DemoApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  // Add an item to the cache
  public void addItem(String key, String value, long ttlSeconds) {
    System.out.println("Addingggggg");
    redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
  }

  // Get an item from the cache
  public String getItem(String key) {
    Object value = redisTemplate.opsForValue().get(key);
    return value != null ? value.toString() : null;
  }
}
