package com.protest.protesting.controller;

import com.protest.protesting.entity.AccountEntity;
import com.protest.protesting.mapper.AccountMapper;
import com.protest.protesting.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

@RestController
public class PongTestController {

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/targetPing/{seq}")
    public void pubPing(@PathVariable("seq") String seq) {
        Jedis jedis = new RedisUtil().getInstance();
        jedis.publish("TPing", seq);

        jedis.close();
    }

    @GetMapping("/tesing/hget/{channel}/{key}")
    public Object testingData(@PathVariable("channel") String channel, @PathVariable("key") String key) {
        Jedis jedis = new RedisUtil().getInstance();

        String hashData = jedis.hget(channel, key);

        return hashData;
    }
}
