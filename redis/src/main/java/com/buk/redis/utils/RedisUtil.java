package com.buk.redis.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO: Redis工具
 *
 * @author BuK
 * @see com.buk.redis.utils.RedisUtilTest
 * @since 2020/08/22
 */
@Component
public class RedisUtil extends RedisBaseUtil<String> {

    public RedisUtil(@Qualifier("redisTemplate") RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }
}
