package com.buk.mongodb.service.impl;

import com.buk.mongodb.pojo.entity.testdb.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * TODO: MongoDB - (新增)文档测试
 * <p>
 * /insert /save
 */
@Slf4j
@SpringBootTest
class InsertDocumentServiceImplTest {

    private static final String COLLECTION_NAME_1 = "users1";
    private static final String COLLECTION_NAME_2 = "users2";
    private static final String COLLECTION_NAME_3 = "users3";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入【一条】数据，如果文档信息已经存在就【抛出异常】
     */
    @Test
    void insert() {
        // 设置用户信息
        User user = new User(null, "1", "男", 1, new Date(), "111");
        // 插入一条用户数据，如果文档信息已经存在就抛出异常
        User insert = mongoTemplate.insert(user);
        User inset1 = mongoTemplate.insert(user, COLLECTION_NAME_1);
        User inset2 = mongoTemplate.insert(user, COLLECTION_NAME_2);
        User inset3 = mongoTemplate.insert(user, COLLECTION_NAME_3);
        // 输出存储结果
        log.info("[MongoDB - (新增)文档测试]: {}", user);
    }

    /**
     * 插入【多条】数据，如果文档信息已经存在就【抛出异常】
     */
    @Test
    void insertAll() {
        // 设置用户信息
        User user1 = new User(null, "2", "女", 2, new Date(), "222");
        User user2 = new User(null, "3", "男", 3, new Date(), "333");
        //
        // 使用户信息加入结合
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        // 插入一条用户数据，如果某个文档信息已经存在就抛出异常
        Collection<User> insertMany = mongoTemplate.insertAll(userList);
        Collection<User> insertMany1 = mongoTemplate.insert(userList, COLLECTION_NAME_1);
        Collection<User> insertMany2 = mongoTemplate.insert(userList, COLLECTION_NAME_2);
        Collection<User> insertMany3 = mongoTemplate.insert(userList, COLLECTION_NAME_3);
        // 输出存储结果
        log.info("[MongoDB - (新增)文档测试]: {}", userList);
    }

    /**
     * 插入【一条】数据，如果文档信息已经存在就【执行更新】
     */
    @Test
    void save() {
        // 设置用户信息
        User user = new User(null, "4", "女", 4, new Date(), "444");
        // 存储用户信息,如果文档信息已经存在就执行更新
        User save = mongoTemplate.save(user);
        User save1 = mongoTemplate.save(user, COLLECTION_NAME_1);
        User save2 = mongoTemplate.save(user, COLLECTION_NAME_2);
        User save3 = mongoTemplate.save(user, COLLECTION_NAME_3);
        // 输出存储结果
        log.info("[MongoDB - (新增)文档测试]: {}", user);
    }
}