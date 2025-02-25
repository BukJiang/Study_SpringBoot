package com.buk.redis.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO: Redis工具
 *
 * @author BuK
 * @see com.buk.example.utils.RedisUtilTest
 * @since 2020/08/22
 */
//@Component
public class Redis1Util extends RedisBaseUtil<String> {

    public Redis1Util(@Qualifier("redis1Template") RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }
}
