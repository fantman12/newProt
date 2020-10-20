package com.protest.protesting;

import com.protest.protesting.mapper.AccountMapper;
import com.protest.protesting.property.FileUploadProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.protest.protesting.utils.RedisUtil;
import redis.clients.jedis.JedisPubSub;


//@MapperScan("com.protest.protesting.mapper")
@SpringBootApplication
@EnableConfigurationProperties({
        FileUploadProperties.class
})
public class ProtestingApplication implements CommandLineRunner {

    @Autowired
    private AccountMapper accountMapper;

    public static String Schannel = null;
    public static String Smessage = null;

    public static void main(String[] args) {
        SpringApplication.run(ProtestingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Time:" + accountMapper.getTime());
    }


    public static void redisConnect() {
        // Redis Connection Reload 처리부
        // Pool load 조금 문제있는듯..
        String IP = "localhost";
        int PORT = 6379;
        int TIMEOUT = 1000;

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, IP, PORT, TIMEOUT);
        Jedis jedis = jedisPool.getResource();

        jedis.publish("blockChannel", "target pong");


    }

    public static void subPing() {
        // Instance 2개로 분리함 CrossInstance road 문제발생 수정완료
        Jedis jedis = new RedisUtil().getInstance();
        Jedis SetJedis = new RedisUtil().getInstance();

        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel);
                System.out.println(message);

                Schannel = channel;
                Smessage = message;

                SetJedis.hset(Schannel, Smessage, "data init");
            }}, "TPing"
        );
        jedis.close();
    }
}
