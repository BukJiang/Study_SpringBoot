package com.buk.redis.config;

import com.buk.redis.property.Redis1Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * TODO: 多数据源(Lettuce)配置
 *
 * @author BuK
 * @since 2020/08/22
 */
//@Configuration
public class Redis1Config {

    @Autowired
    private Redis1Properties redis1Properties;

    /**
     * lettuceConnection1Factory
     *
     * @return
     */
    @Bean
    @Primary
    public LettuceConnectionFactory lettuceConnection1Factory() {
        return RedisConfigUtil.Build.lettuceConnectionFactory(redis1Properties);
    }

    /**
     * Redis1Template
     *
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate<String, String> redis1Template(
            @Qualifier("lettuceConnection1Factory") LettuceConnectionFactory lettuceConnectionFactory
    ) {
        return RedisConfigUtil.Build.lettuceStringRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * TODO: [方式1] 缓存管理(根据cacheName定义缓存配置)
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    @Primary
    public CacheManager cacheManager(@Qualifier("lettuceConnection1Factory") RedisConnectionFactory connectionFactory) {
        //
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                // 根据cacheName定义缓存配置
                .withCacheConfiguration("CacheConfig:User:100s", RedisConfigUtil.Config.redisCache(Duration.ofSeconds(100)))
                .withCacheConfiguration("CacheConfig:User:200s", RedisConfigUtil.Config.redisCache(Duration.ofSeconds(200)))
                .withCacheConfiguration("CacheConfig:User:300s", RedisConfigUtil.Config.redisCache(Duration.ofSeconds(300)))
                // 配置同步修改或删除 put/evict
                .transactionAware()
                .build();
    }

    /**
     * TODO: [方式2] 缓存管理(根据cacheName定义缓存配置)
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cache400s(@Qualifier("lettuceConnection1Factory") RedisConnectionFactory connectionFactory) {
        //
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                // 固定配置
                .cacheDefaults(RedisConfigUtil.Config.redisCache(Duration.ofSeconds(400)))
                // 配置同步修改或删除 put/evict
                .transactionAware()
                .build();
    }

    /**
     * TODO: [方式3] 缓存管理(根据#表识，动态定义缓存配置)
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManagerConfig(@Qualifier("lettuceConnection1Factory") RedisConnectionFactory connectionFactory) {
        // RedisCacheConfiguration
        RedisCacheConfiguration redisCacheConfiguration = RedisConfigUtil.Config.redisCache(Duration.ofDays(1));
        // RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        // CacheManager
        return new RedisCacheManagerConfig(redisCacheWriter, redisCacheConfiguration);
    }
}
