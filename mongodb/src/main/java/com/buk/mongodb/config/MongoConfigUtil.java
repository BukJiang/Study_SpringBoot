package com.buk.mongodb.config;

import com.mongodb.client.MongoClients;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

/**
 * TODO: MongoDB配置工具
 *
 * @author BuK
 * @since 2020/08/28
 */
public class MongoConfigUtil {

    /**
     * 数据库工厂
     */
    public static class DatabaseFactory {

        private static final String MONGODB_PREFIX = "mongodb://";

        /**
         * 初始化
         * <p>
         * mongodb://
         * [username:password@]
         * host1[:port1][,host2[:port2],...[,hostN[:portN]]]
         * [/[database.collection]
         * [?options]]
         *
         * @param mongoProperties
         * @return
         */
        public static MongoDatabaseFactory init(MongoProperties mongoProperties) {
            String connectionString = MONGODB_PREFIX;
            // 用户名 密码
            if (StringUtils.isNotBlank(mongoProperties.getUsername())
                    && StringUtils.isNotBlank(String.valueOf(mongoProperties.getPassword()))) {
                connectionString += mongoProperties.getUsername() + ":" + String.valueOf(mongoProperties.getPassword()) + "@";
            }
            // 地址 端口 数据库
            connectionString +=
                    mongoProperties.getHost() + ":" + mongoProperties.getPort()
                            + "/" + mongoProperties.getDatabase();
            //
            return new SimpleMongoClientDatabaseFactory(
                    MongoClients.create(connectionString),
                    mongoProperties.getDatabase()
            );
        }
    }

}
