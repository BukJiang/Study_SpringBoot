package com.buk.mongodb.service.impl;

import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.validation.Validator;

import java.util.Collections;
import java.util.Set;

/**
 * TODO: MongoDB - 集合测试
 * <p>
 * /getCollectionNames /collectionExists /drop /createCollection
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollectionServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询
     */
    @Test
    @Order(3)
    void query() {
        // 获取集合名称列表
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        log.info("[MongoDB - 集合测试]: 集合列表{}", Collections.singletonList(collectionNames));

        // 检测集合是否存在
        String collectionName = "users1";
        boolean b = mongoTemplate.collectionExists(collectionName);
        log.info("[MongoDB - 集合测试]: users1集合是否存在-{}", b);
    }

    /**
     * 删除
     */
    @Test
    @Order(1)
    void drop() {
        // 设置集合名称
        String collectionName = "users1";
        // 执行删除集合
        mongoTemplate.getCollection(collectionName).drop();
        // 检测新的集合是否存在，返回删除结果
        if (mongoTemplate.collectionExists(collectionName)) {
            log.info("[MongoDB - 集合测试]: 删除集合users1失败！");
        } else {
            log.info("[MongoDB - 集合测试]: 删除集合users1成功！");
        }
    }

    /**
     * 1. 创建没有限制大小的集合（默认集合创建方式）
     */
    @Test
    @Order(2)
    void createCollection1() {
        // 设置集合名称
        String collectionName = "users1";
        // 执行删除集合
        mongoTemplate.getCollection(collectionName).drop();
        // 创建集合并返回集合信息
        mongoTemplate.createCollection(collectionName);
        // 检测新的集合是否存在，返回创建结果
        if (mongoTemplate.collectionExists(collectionName)) {
            log.info("[MongoDB - 集合测试]: 创建集合users1成功！");
        } else {
            log.info("[MongoDB - 集合测试]: 创建集合users1失败！");
        }
    }

    /**
     * 2. 创建固定大小集合，创建集合并设置 capped=true，
     * 可以配置参数 size 限制文档大小，可以配置参数 max 限制集合文档数量
     */
    @Test
    @Order(2)
    void createCollection2() {
        // 设置集合名称
        String collectionName = "users2";
        // 执行删除集合
        mongoTemplate.getCollection(collectionName).drop();
        // 设置集合参数
        long size = 1024L;
        long max = 3L;
        // 创建固定大小集合
        CollectionOptions collectionOptions = CollectionOptions
                .empty()
                // 创建固定集合。固定集合是指有着固定大小的集合，当达到最大值时，它会自动覆盖最早的文档。
                .capped()
                // 固定集合指定一个最大值，以千字节计(KB),如果 capped 为 true，也需要指定该字段。
                .size(size)
                // 指定固定集合中包含文档的最大数量。
                .maxDocuments(max);
        // 执行创建集合
        mongoTemplate.createCollection(collectionName, collectionOptions);
        // 检测新的集合是否存在，返回创建结果
        if (mongoTemplate.collectionExists(collectionName)) {
            log.info("[MongoDB - 集合测试]: 创建集合users2成功！");
        } else {
            log.info("[MongoDB - 集合测试]: 创建集合users2失败！");
        }
    }

    /**
     * 3. 创建集合并在文档"插入"与"更新"时进行数据效验，
     * 如果符合创建集合设置的条件就进允许更新与插入，否则则按照设置的策略进行处理
     * <p>
     * * 效验级别：
     * -- off：关闭数据校验。
     * -- strict：(默认值) 对所有的文档"插入"与"更新"操作有效。
     * -- moderate：仅对"插入"和满足校验规则的"文档"做"更新"操作有效。对已存在的不符合校验规则的"文档"无效。
     * * 执行策略：
     * -- error：(默认值) 文档必须满足校验规则，才能被写入。
     * -- warn：对于"文档"不符合校验规则的 MongoDB 允许写入，但会记录一条告警到 mongodb.log 中去。
     * 日志内容记录报错信息以及该"文档"的完整记录。
     */
    @Test
    @Order(3)
    void createCollection3() {
        // 设置集合名称
        String collectionName = "users3";
        // 执行删除集合
        mongoTemplate.getCollection(collectionName).drop();
        // 设置验证条件,只允许岁数大于20的用户信息插入
        CriteriaDefinition criteriaDefinition = Criteria.where("age").gt(0);
        // 设置集合选项验证对象
        CollectionOptions collectionOptions = CollectionOptions
                .empty()
                // 校验器
                .validator(Validator.criteria(criteriaDefinition))
                // 设置效验级别
                .strictValidation()
                // 设置效验不通过后执行的动作
                .failOnValidationError();
        // 执行创建集合
        mongoTemplate.createCollection(collectionName, collectionOptions);
        // 检测新的集合是否存在，返回创建结果
        if (mongoTemplate.collectionExists(collectionName)) {
            log.info("[MongoDB - 集合测试]: 创建集合users3成功！");
        } else {
            log.info("[MongoDB - 集合测试]: 创建集合users3失败！");
        }
    }

    /**
     * 创建索引
     */
    @Test
    void createIndex() {
        // 创建索引
        mongoTemplate.getCollection("users")
                .createIndex(Indexes.ascending("sex"));
    }
}