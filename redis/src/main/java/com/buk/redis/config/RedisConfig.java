package com.buk.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO: redis配置
 *
 * @author BuK
 * @since 2020/08/22
 */
@Configuration
public class RedisConfig {

    /**
     * RedisTemplate
     *
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return RedisConfigUtil.Build.lettuceStringRedisTemplate(redisConnectionFactory);
    }
}
