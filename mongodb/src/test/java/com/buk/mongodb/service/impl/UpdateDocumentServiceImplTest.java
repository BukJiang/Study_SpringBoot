package com.buk.mongodb.service.impl;

import com.buk.mongodb.pojo.entity.testdb.User;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * TODO: MongoDB - (更新)文档测试
 * <p>
 * /upsert /updateFirst /updateMulti
 */
@Slf4j
@SpringBootTest
class UpdateDocumentServiceImplTest {

    private static final String COLLECTION_NAME_1 = "users1";
    private static final String COLLECTION_NAME_2 = "users2";
    private static final String COLLECTION_NAME_3 = "users3";

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】
     */
    @Test
    void upsert() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(30);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 创建更新对象，并设置更新的内容
        Update update = new Update()
                .set("age", 33)
                .set("name", "Update");
        // 执行更新，如果没有找到匹配查询的文档，则创建并插入一个新文档
        UpdateResult updateResult = mongoTemplate.upsert(query, update, User.class);
        // 输出结果信息
        String resultInfo = "匹配到" + updateResult.getMatchedCount() + "条数据，对第一条数据进行了更改";
        log.info("[MongoDB - (更新)文档测试]: {}", resultInfo);
    }

    /**
     * 更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】
     */
    @Test
    void updateFirst() {
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is("0");
        // 创建查询对象，然后将条件对象添加到其中，并设置排序
        Query query = new Query(criteria).with(Sort.by("age").ascending());
        // 创建更新对象,并设置更新的内容
        Update update = new Update()
                .set("age", 30)
                .set("name", "UpdateFirst");
        // 执行更新
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        // 输出结果信息
        String resultInfo = "共匹配到" + updateResult.getMatchedCount() + "条数据，修改了" + updateResult.getModifiedCount() + "条数据";
        log.info("[MongoDB - (更新)文档测试]: {}", resultInfo);
    }

    /**
     * 更新【匹配查询】到的【文档数据集合】中的【所有数据】
     */
    @Test
    void updateMany() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").gt(31);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 设置更新字段和更新的内容
        Update update = new Update()
                .set("age", 29)
                .set("salary", "1999");
        // 执行更新
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, User.class);
        // 输出结果信息
        String resultInfo = "总共匹配到" + updateResult.getMatchedCount() + "条数据，修改了" + updateResult.getModifiedCount() + "条数据";
        log.info("[MongoDB - (更新)文档测试]: {}", resultInfo);
    }
}