package com.buk.mongodb.service.impl.more;

import com.buk.mongodb.pojo.entity.testdb.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

/**
 * TODO: MongoDB - 读数据源 (新增)文档测试
 * <p>
 * /insert /save
 */
@SpringBootTest
class MoreInsertDocumentServiceImplTest {

    @Autowired
    @Qualifier("mongo1Template")
    private MongoTemplate mongo1Template;

    @Autowired
    @Qualifier("mongo2Template")
    private MongoTemplate mongo2Template;

    /**
     * 插入【一条】数据，如果文档信息已经存在就【抛出异常】
     */
    @Test
    void insert() {
        // 设置用户信息
        User user = new User(null, "1", "男", 1, new Date(), "111");
        // 插入一条用户数据，如果文档信息已经存在就抛出异常
        User insert1 = mongo1Template.insert(user);
        User insert2 = mongo2Template.insert(user);
    }
}
