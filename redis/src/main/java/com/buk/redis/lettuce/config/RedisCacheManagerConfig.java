package com.buk.redis.lettuce.config;

import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Map;

/**
 * TODO: Redis缓存管理配置
 * TODO: 反射
 *
 * @author BuK
 * @since 2020/08/22
 */
@Slf4j
public class RedisCacheManagerConfig extends RedisCacheManager {

    public RedisCacheManagerConfig(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        log.info("[Redis缓存管理配置]: 实例化");
    }

    public RedisCacheManagerConfig(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public RedisCacheManagerConfig(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public RedisCacheManagerConfig(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public RedisCacheManagerConfig(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        log.info("[Redis缓存管理配置]: " + name);
        // 自定义策略
        String[] split = StringUtils.split(name, "#");
        switch (split.length) {
            case 1:
                break;
            case 2:
                if (!StringUtils.isNumeric(split[1])) {
                    throw new RedisException("[Redis缓存管理配置]: cacheName的#标识之后只能是数字，用来标注缓存时间。(例如：cacheName#100,表示cacheName这个key缓存100s)");
                }
                // cacheName
                name = split[0];
                // 反射修改 Duration.seconds 的值
                Long cacheTime = Long.parseLong(split[1]);
                Duration duration = cacheConfig.getTtl();
                try {
                    Field field = duration.getClass().getDeclaredField("seconds");
                    field.setAccessible(true);
                    field.set(duration, cacheTime);
                    field.setAccessible(false);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RedisException("[Redis缓存管理配置]: 反射修改Duration的值失败！");

                }
                break;
            default:
                throw new RedisException("[Redis缓存管理配置]: cacheName至多只能存在1个#标识，用来标注缓存时间。(例如：cacheName#100,表示cacheName这个key缓存100s)");
        }
        return super.createRedisCache(name, cacheConfig);
    }
}