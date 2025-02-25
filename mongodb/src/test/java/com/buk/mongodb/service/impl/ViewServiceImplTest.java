package com.buk.mongodb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: MongoDB - 视图测试
 * <p>
 * /drop /createView
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ViewServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 删除
     */
    @Test
    @Order(1)
    void drop() {
        // 设置待删除的视图名称
        String viewName = "users1View";
        // 删除视图
        mongoTemplate.getDb().getCollection(viewName).drop();
        // 检测新的视图是否存在，返回创建结果
        if (mongoTemplate.collectionExists(viewName)) {
            log.info("[MongoDB - 视图测试]: 删除视图users1View失败！");
        } else {
            log.info("[MongoDB - 视图测试]: 删除视图users1View成功！");
        }
    }

    /**
     * 创建
     */
    @Test
    @Order(2)
    void createView() {
        // 设置视图名
        String newViewName = "users1View";
        // 删除视图
        mongoTemplate.getDb().getCollection(newViewName).drop();
        // 设置获取数据的集合名称
        String collectionName = "users1";
        // 定义视图的管道,可是设置视图显示的内容多个筛选条件
        List<Bson> pipeline = new ArrayList<>();
        // 设置条件，用于筛选集合中的文档数据，只有符合条件的才会映射到视图中
        pipeline.add(Document.parse("{\"$match\":{\"sex\":\"女\"}}"));
        // 执行创建视图
        mongoTemplate.getDb().createView(newViewName, collectionName, pipeline);
        // 检测新的视图是否存在，返回创建结果
        if (mongoTemplate.collectionExists(newViewName)) {
            log.info("[MongoDB - 视图测试]: 创建视图users1View成功！");
        } else {
            log.info("[MongoDB - 视图测试]: 创建视图users1View失败！");
        }
    }
}