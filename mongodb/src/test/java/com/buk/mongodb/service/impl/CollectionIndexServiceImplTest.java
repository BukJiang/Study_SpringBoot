package com.buk.mongodb.service.impl;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * TODO: MongoDB - 集合索引测试
 * <p>
 * /createIndex /listIndexes /dropIndex /dropIndexes
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CollectionIndexServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "users";

    /**
     * 创建索引
     */
    @Test
    void createIndex() {
        // 升序索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.ascending("sex"));

        // 升序复合索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.ascending("sex", "age"));

        // 文字索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.text("name"));

        // 哈希索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.hashed("name"));

        // 升序唯一索引
        // 配置索引选项
        IndexOptions options1 = new IndexOptions();
        // 设置为唯一索引
        options1.unique(true);
        // 创建索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.ascending("age"), options1);

        // 局部索引
        // 配置索引选项
        IndexOptions options2 = new IndexOptions();
        // 设置过滤条件
        options2.partialFilterExpression(Filters.exists("name", true));
        // 创建索引
        mongoTemplate.getCollection(COLLECTION_NAME)
                .createIndex(Indexes.ascending("name"), options2);
    }

    /**
     * 获取当前【集合】对应的【所有索引】的【名称列表】
     */
    @Test
    void listIndexes() {
        // 获取集合中所有列表
        ListIndexesIterable<Document> listIndexesIterable = mongoTemplate.getCollection(COLLECTION_NAME)
                .listIndexes();
        // 获取集合中全部索引信息
        for (Document next : listIndexesIterable) {
            log.info("[MongoDB - 集合索引测试]: {}", next);
        }
    }

    /**
     * 根据索引名称移除索引
     */
    @Test
    void dropIndex() {
        // 删除集合中某个索引
        mongoTemplate.getCollection(COLLECTION_NAME).dropIndex("sex_1");
    }

    /**
     * 移除全部索引
     */
    @Test
    void dropIndexes() {
        // 删除集合中全部索引
        mongoTemplate.getCollection(COLLECTION_NAME).dropIndexes();
    }
}