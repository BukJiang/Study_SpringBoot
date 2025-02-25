package com.buk.mongodb.service.impl;

import com.buk.mongodb.pojo.entity.testdb.User;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * TODO: MongoDB - (移除)文档测试
 * <p>
 * /remove
 */
@Slf4j
@SpringBootTest
class RemoveDocumentServiceImplTest {

    private static final String COLLECTION_NAME_1 = "users1";
    private static final String COLLECTION_NAME_2 = "users2";
    private static final String COLLECTION_NAME_3 = "users3";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 删除集合中【符合条件】的【一个]或[多个】文档
     */
    @Test
    void remove() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(1).and("sex").is("男");
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档信息
        DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
        // 输出结果信息
        String resultInfo = "成功删除 " + deleteResult.getDeletedCount() + " 条文档信息";
        log.info("[MongoDB - (移除)文档测试]: {}", resultInfo);
    }

    /**
     * 删除【符合条件】的【单个文档】，并返回删除的文档
     */
    @Test
    void findAndRemove() {
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is("Update");
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的第一条文档,并返回删除的文档信息
        User result = mongoTemplate.findAndRemove(query, User.class);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + result;
        log.info("[MongoDB - (移除)文档测试]: {}", resultInfo);
    }

    /**
     * 删除【符合条件】的【全部文档】，并返回删除的文档
     */
    @Test
    void findAllAndRemove() {
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is("name");
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档,并返回删除的全部文档信息
        List<User> resultList = mongoTemplate.findAllAndRemove(query, User.class);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + resultList;
        log.info("[MongoDB - (移除)文档测试]: {}", resultInfo);
    }
}