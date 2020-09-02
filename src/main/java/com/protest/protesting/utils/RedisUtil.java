package com.protest.protesting.utils;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RequiredArgsConstructor
public class RedisUtil {


    public Jedis getInstance() {
        String IP = "localhost";
        int PORT = 6379;
        int TIMEOUT = 1000;

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, IP, PORT, TIMEOUT);

        return jedisPool.getResource();
    }
}
