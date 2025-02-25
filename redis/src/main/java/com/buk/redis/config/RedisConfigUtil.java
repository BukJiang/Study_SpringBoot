package com.buk.redis.config;

import com.buk.redis.property.RedisBaseProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * TODO: Redis配置工具
 *
 * @author BuK
 * @since 2020/08/22
 */
public class RedisConfigUtil {

    /**
     * 构建类
     */
    public static class Build {

        /**
         * 构建 Lettuce连接工厂
         *
         * @param redisBaseProperties
         * @return
         */
        public static LettuceConnectionFactory lettuceConnectionFactory(
                RedisBaseProperties redisBaseProperties
        ) {
            // 基本配置
            RedisStandaloneConfiguration redisStandaloneConfiguration = Config
                    .redisStandalone(
                            redisBaseProperties.getHost(),
                            redisBaseProperties.getPort(),
                            redisBaseProperties.getDatabase(),
                            redisBaseProperties.getPassword()
                    );
            // 连接池通用配置
            RedisBaseProperties.Pool pool = redisBaseProperties.getLettuce().getPool();
            GenericObjectPoolConfig<LettuceConnectionFactory> genericObjectPoolConfig = Config
                    .genericObjectPool(
                            pool.getMaxActive(), pool.getMaxIdle(), pool.getMinIdle(), pool.getMaxWait().toMillis()
                    );
            // Lettuce连接工厂
            return ConnectionFactory.lettuce(
                    redisStandaloneConfiguration, genericObjectPoolConfig, redisBaseProperties.getTimeout()
            );
        }

        /**
         * 构建 RedisTemplate
         * 序列化 StringRedisSerializer
         *
         * @return
         */
        public static RedisTemplate<String, String> lettuceStringRedisTemplate(
                RedisConnectionFactory redisConnectionFactory
        ) {
            return Build.initStringRedisTemplate(redisConnectionFactory);
        }

        /**
         * 构建 RedisTemplate
         * 序列化 Jackson2JsonRedisSerializer
         *
         * @return
         */
        public static RedisTemplate<String, Object> lettuceJackson2JsonRedisSerializerRedisTemplate(
                RedisBaseProperties redisBaseProperties
        ) {
            LettuceConnectionFactory lettuceConnectionFactory = lettuceConnectionFactory(redisBaseProperties);
            return Build.initJackson2JsonRedisSerializerRedisTemplate(lettuceConnectionFactory);
        }

        /**
         * 初始化 (StringRedisSerializer + RedisTemplate)
         *
         * @param factory
         * @return
         */
        private static RedisTemplate<String, String> initStringRedisTemplate(
                RedisConnectionFactory factory
        ) {
            // 创建 RedisTemplate
            RedisTemplate<String, String> template = new RedisTemplate<>();
            template.setConnectionFactory(factory);

            // 序列化
            // key
            template.setKeySerializer(StringRedisSerializer.UTF_8);
            // hashKey
            template.setHashKeySerializer(StringRedisSerializer.UTF_8);
            // value
            template.setValueSerializer(StringRedisSerializer.UTF_8);
            // hashValue
            template.setHashValueSerializer(StringRedisSerializer.UTF_8);
            //
            return template;
        }

        /**
         * 初始化 (Jackson2JsonRedisSerializer + RedisTemplate)
         *
         * @param factory
         * @return
         */
        private static RedisTemplate<String, Object> initJackson2JsonRedisSerializerRedisTemplate(
                RedisConnectionFactory factory
        ) {
            // 创建 RedisTemplate
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            template.setConnectionFactory(factory);

            // 创建 Jackson2JsonRedisSerializer
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = Serializer.jackson2JsonRedis();

            // 序列化
            // key
            template.setKeySerializer(StringRedisSerializer.UTF_8);
            // hashKey
            template.setHashKeySerializer(StringRedisSerializer.UTF_8);
            // value
            template.setValueSerializer(jackson2JsonRedisSerializer);
            // hashValue
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            //
            return template;
        }
    }

    /**
     * 配置类
     */
    public static class Config {
        /**
         * redis缓存配置
         *
         * @param ttl
         * @return
         */
        public static RedisCacheConfiguration redisCache(Duration ttl) {
            // 创建 Jackson2JsonRedisSerializer
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = Serializer.jackson2JsonRedis();
            // RedisCacheConfiguration
            return RedisCacheConfiguration
                    .defaultCacheConfig()
                    .disableCachingNullValues()
                    // 配置序列化
                    .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer.UTF_8))
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                    // 过期时间
                    .entryTtl(ttl);
        }

        /**
         * 基本配置
         *
         * @param host
         * @param port
         * @param database
         * @param password
         * @return
         */
        private static RedisStandaloneConfiguration redisStandalone(
                String host, int port, int database, String password
        ) {
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
            configuration.setHostName(host);
            configuration.setPort(port);
            configuration.setDatabase(database);
            configuration.setPassword(RedisPassword.of(password));
            return configuration;
        }

        /**
         * 连接池通用配置
         *
         * @param maxActive
         * @param maxIdle
         * @param minIdle
         * @param maxWaitMillis
         * @param <T>
         * @return
         */
        private static <T> GenericObjectPoolConfig<T> genericObjectPool(
                int maxActive, int maxIdle, int minIdle, long maxWaitMillis
        ) {
            GenericObjectPoolConfig<T> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
            genericObjectPoolConfig.setMaxTotal(maxActive);
            genericObjectPoolConfig.setMaxIdle(maxIdle);
            genericObjectPoolConfig.setMinIdle(minIdle);
            genericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
            return genericObjectPoolConfig;
        }
    }

    /**
     * 序列化类
     */
    public static class Serializer {

        /**
         * 创建 Jackson2JsonRedisSerializer
         *
         * @return
         */
        public static Jackson2JsonRedisSerializer<Object> jackson2JsonRedis() {
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer
                    = new Jackson2JsonRedisSerializer<>(Object.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(
                    PropertyAccessor.ALL,
                    JsonAutoDetect.Visibility.ANY
            );
            objectMapper.activateDefaultTyping(
                    objectMapper.getPolymorphicTypeValidator(),
                    ObjectMapper.DefaultTyping.NON_FINAL
            );
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
            return jackson2JsonRedisSerializer;
        }
    }

    /**
     * 连接工厂类
     */
    public static class ConnectionFactory {

        /**
         * Lettuce连接工厂
         *
         * @param redisStandaloneConfigurationInit
         * @param genericObjectPoolConfig
         * @param timeout
         * @return
         */
        private static LettuceConnectionFactory lettuce(
                RedisStandaloneConfiguration redisStandaloneConfigurationInit,
                GenericObjectPoolConfig<LettuceConnectionFactory> genericObjectPoolConfig,
                Duration timeout
        ) {
            // 创建 lettuce连接池
            LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder
                    = LettucePoolingClientConfiguration.builder();
            //
            builder.poolConfig(genericObjectPoolConfig);
            builder.commandTimeout(timeout);
            // 连接工厂
            return new LettuceConnectionFactory(redisStandaloneConfigurationInit, builder.build());
        }
    }
}
