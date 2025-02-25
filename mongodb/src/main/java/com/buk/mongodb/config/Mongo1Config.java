package com.buk.mongodb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * TODO: MongoDB 多数据源配置
 * <p>
 * TODO: 单节点 MongoDB 不支持事务，需要搭建 MongoDB 复制集。
 *
 * @author BuK
 * @since 2020/08/22
 */
//@Configuration
public class Mongo1Config {

    /**
     * MongoProperties
     *
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.data.mongodb.db1")
    public MongoProperties mongo1Properties() {
        return new MongoProperties();
    }

    /**
     * MongoDatabaseFactory
     *
     * @param mongo1Properties
     * @return
     */
    @Bean
    @Primary
    public MongoDatabaseFactory mongoDatabase1Factory(
            @Qualifier("mongo1Properties") MongoProperties mongo1Properties
    ) {
        return MongoConfigUtil.DatabaseFactory.init(mongo1Properties);
    }

    /**
     * 事务配置
     *
     * @param mongoDatabase1Factory
     * @return
     */
    @Bean
    @Primary
    MongoTransactionManager mongoTransaction1Manager(
            @Qualifier("mongoDatabase1Factory") MongoDatabaseFactory mongoDatabase1Factory
    ) {
        return new MongoTransactionManager(mongoDatabase1Factory);
    }

    /**
     * MongoTemplate
     *
     * @param mongoDatabase1Factory
     * @return
     */
    @Bean
    @Primary
    public MongoTemplate mongo1Template(
            @Qualifier("mongoDatabase1Factory") MongoDatabaseFactory mongoDatabase1Factory
    ) {
        return new MongoTemplate(mongoDatabase1Factory);
    }
}
