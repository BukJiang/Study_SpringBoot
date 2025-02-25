package com.buk.redis.lettuce.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO: Redis属性配置
 *
 * @author BuK
 * @since 2020/08/22
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.db1")
public class Redis1Properties extends RedisBaseProperties {

}
