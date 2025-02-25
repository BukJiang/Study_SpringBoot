package com.buk.redis.lettuce.config;

import com.buk.redis.lettuce.properties.Redis2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO: 多数据源(Lettuce)配置
 *
 * @author BuK
 * @since 2020/08/22
 */
//@Configuration
public class Redis2Config {

    @Autowired
    private Redis2Properties redis2Properties;

    /**
     * lettuceConnection2Factory
     *
     * @return
     */
    @Bean
    public LettuceConnectionFactory lettuceConnection2Factory() {
        return RedisConfigUtil.Build.lettuceConnectionFactory(redis2Properties);
    }

    /**
     * Redis1Template
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redis2Template(
            @Qualifier("lettuceConnection2Factory") LettuceConnectionFactory lettuceConnectionFactory
    ) {
        return RedisConfigUtil.Build.lettuceStringRedisTemplate(lettuceConnectionFactory);
    }
}
