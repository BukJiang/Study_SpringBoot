package com.buk.redis.lettuce.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO: Redis工具
 *
 * @author BuK
 * @see com.buk.redis.lettuce.utils.RedisUtilTest
 * @since 2020/08/22
 */
@Component
public class Redis1Util extends RedisBaseUtil<String> {

    public Redis1Util(@Qualifier("redis1Template") RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate);
    }
}
