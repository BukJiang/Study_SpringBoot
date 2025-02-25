package com.buk.redis.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilTest {

//    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Redis1Util redisUtil1;

    @Autowired
    private Redis2Util redisUtil2;

    @Test
    void serialize() {
        redisUtil.set("setKey-String", "setValue");
        redisUtil.lPush("lLPushKey-String", "lLPushValue");
        redisUtil.set("sSetKey-String", "sSetValue");
        redisUtil.zAdd("zsAddKey-String", "zsAddValue", 1d);
        redisUtil.hSet("hSetKey-String", "hSetHashKey-String", "hSetValue");
    }

    @Test
    void moreRedis() {
        redisUtil1.set("db1", "value-1");
        redisUtil1.expire("db1", 60L);

        redisUtil2.set("db2", "value-2");
        redisUtil2.expire("db2", 60L);
    }
}