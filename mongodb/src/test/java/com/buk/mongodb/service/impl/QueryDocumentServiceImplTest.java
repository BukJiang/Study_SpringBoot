package com.buk.mongodb.service.impl;

import com.buk.mongodb.pojo.entity.testdb.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * TODO: MongoDB - (查询)文档测试
 * <p>
 * /findAll /findById /findOne /find /count
 */
@Slf4j
@SpringBootTest
class QueryDocumentServiceImplTest {

    private static final String COLLECTION_NAME_1 = "users1";
    private static final String COLLECTION_NAME_2 = "users2";
    private static final String COLLECTION_NAME_3 = "users3";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询集合中的【全部】文档数据
     */
    @Test
    void findAll() {
        // 执行查询集合中全部文档信息
        List<User> finAll = mongoTemplate.findAll(User.class);
        List<User> finAll1 = mongoTemplate.findAll(User.class, COLLECTION_NAME_1);
        List<User> finAll2 = mongoTemplate.findAll(User.class, COLLECTION_NAME_2);
        List<User> finAll3 = mongoTemplate.findAll(User.class, COLLECTION_NAME_3);
        // 输出结果
        log.info("[MongoDB - (查询)文档测试]: finAll");
        for (User user : finAll) {
            log.info("[MongoDB - (查询)文档测试]: {}", user);
        }
        log.info("[MongoDB - (查询)文档测试]: finAll1");
        for (User user : finAll1) {
            log.info("[MongoDB - (查询)文档测试]: {}", user);
        }
        log.info("[MongoDB - (查询)文档测试]: finAll2");
        for (User user : finAll2) {
            log.info("[MongoDB - (查询)文档测试]: {}", user);
        }
        log.info("[MongoDB - (查询)文档测试]: finAll3");
        for (User user : finAll3) {
            log.info("[MongoDB - (查询)文档测试]: {}", user);
        }
    }

    /**
     * 根据【文档ID】查询集合中文档数据
     */
    @Test
    void findById() {
        // 设置查询的文档 ID
        String id = "1";
        // 根据文档ID查询集合中文档数据，并转换为对应 Java 对象
        User user = mongoTemplate.findById(id, User.class);
        User user1 = mongoTemplate.findById(id, User.class, COLLECTION_NAME_1);
        User user2 = mongoTemplate.findById(id, User.class, COLLECTION_NAME_2);
        User user3 = mongoTemplate.findById(id, User.class, COLLECTION_NAME_3);
        // 输出结果
        log.info("[MongoDB - (查询)文档测试]: {}", user);
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档，只取【第一条】数据
     */
    @Test
    void findOne() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(33);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 返回包含字段
        query.fields().include("age");
        // 返回不包含该字段
        query.fields().exclude("id");
        // 查询一条文档，如果查询结果中有多条文档，那么就取第一条
        User user = mongoTemplate.findOne(query, User.class);
        User user1 = mongoTemplate.findOne(query, User.class, COLLECTION_NAME_1);
        User user2 = mongoTemplate.findOne(query, User.class, COLLECTION_NAME_2);
        User user3 = mongoTemplate.findOne(query, User.class, COLLECTION_NAME_3);
        // 输出结果
        log.info("[MongoDB - (查询)文档测试]: {}", user);
    }

    /**
     * 根据【条件】查询集合中【符合条件】的文档
     * <p>
     * 【sort】按指定字段进行排序
     * 【skip】并跳过指定数目
     * 【limit】限制指定数目
     */
    @Test
    void find() {
        // 设置查询条件参数
        Integer[] ages = {1, 2, 3, 4, 5, 6, 7};
        String regex = "^女*";
        // 创建条件对象
        Criteria criteria11 = Criteria.where("sex").is("男"); // {"sex":"男"}
        Criteria criteria12 = Criteria.where("sex").exists(true); // 存在指定字段名称
        Criteria criteria13 = Criteria.where("age").in((Object) ages); // {"sex":{$in:[1,2,3,4,5,6,7]}}
        Criteria criteria21 = Criteria.where("sex").is("女"); // {"sex":"女"}
        Criteria criteria22 = Criteria.where("age").gt(0); // {"sex":{$gt:0}}
        Criteria criteria23 = Criteria.where("name").regex(regex); // {"sex":{$gt:0}}
        // 创建条件对象，将上面条件进行 AND 关联
        Criteria criteria1 = new Criteria().andOperator(criteria11, criteria12, criteria13);
        Criteria criteria2 = new Criteria().andOperator(criteria21, criteria22, criteria23);
        // 创建条件对象，将上面条件进行 OR 关联
        Criteria criteria = new Criteria().orOperator(criteria1, criteria2);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query()
                .addCriteria(criteria)
                .with(Sort.by("age"))
                .skip(0)
                .limit(10);
        log.info("[MongoDB - (查询)文档测试]: {}", query.toString());
        // 执行查询
        List<User> documentList = mongoTemplate.find(query, User.class);
        // 输出结果
        for (User user : documentList) {
            log.info("[MongoDB - (查询)文档测试]: {}", user);
        }
    }

    /**
     * 统计集合中符合【查询条件】的文档【数量】
     */
    @Test
    void count() {
        // 设置查询条件参数
        int age = 1;
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        log.info("[MongoDB - (查询)文档测试]: {}", query.toString());
        // 查询并返回结果
        long count = mongoTemplate.count(query, User.class);
        // 输出结果
        log.info("[MongoDB - (查询)文档测试]: {}", count);
    }
}