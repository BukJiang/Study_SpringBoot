package com.buk.mongodb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
public class Mongo2Config {

    /**
     * MongoProperties
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.data.mongodb.db2")
    public MongoProperties mongo2Properties() {
        return new MongoProperties();
    }

    /**
     * MongoDatabaseFactory
     *
     * @param mongo2Properties
     * @return
     */
    @Bean
    public MongoDatabaseFactory mongoDatabase2Factory(
            @Qualifier("mongo2Properties") MongoProperties mongo2Properties
    ) {
        return MongoConfigUtil.DatabaseFactory.init(mongo2Properties);
    }

    /**
     * 事务配置
     *
     * @param mongoDatabase2Factory
     * @return
     */
    @Bean
    MongoTransactionManager mongoTransaction2Manager(
            @Qualifier("mongoDatabase2Factory") MongoDatabaseFactory mongoDatabase2Factory
    ) {
        return new MongoTransactionManager(mongoDatabase2Factory);
    }

    /**
     * MongoTemplate
     *
     * @param mongoDatabase2Factory
     * @return
     */
    @Bean
    public MongoTemplate mongo2Template(
            @Qualifier("mongoDatabase2Factory") MongoDatabaseFactory mongoDatabase2Factory
    ) {
        return new MongoTemplate(mongoDatabase2Factory);
    }
}
