package com.buk.mongodb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * TODO: MongoDB - 自定义命令测试
 * <p>
 * /runCommand
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RunCommandServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "users";

    /**
     * 执行 mongoDB 自定义命令，详情可以查看：https://docs.mongodb.com/manual/reference/command/
     */
    @Test
    void runCommand() {
        // 自定义命令
        String jsonCommand = "{\"buildInfo\":1}";
        // 将 JSON 字符串解析成 MongoDB 命令
        Bson bson = Document.parse(jsonCommand);
        // 执行自定义命令
        Document document = mongoTemplate.getDb().runCommand(bson);
        log.info("[MongoDB - 自定义命令测试]: {}", document);
    }
}