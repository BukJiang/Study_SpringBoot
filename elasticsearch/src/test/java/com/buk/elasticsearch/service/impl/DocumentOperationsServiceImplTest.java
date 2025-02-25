package com.buk.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.buk.elasticsearch.pojo.document.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * TODO: Elasticsearch - 文档操作测试
 */
@Slf4j
@SpringBootTest
class DocumentOperationsServiceImplTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    // region DocumentOperations 接口
    @Test
    void documentOperations() {
//        [新增]
//        <T> T save(T entity)
//        <T> T save(T entity, IndexCoordinates index)
//        <T> Iterable<T> save(Iterable<T> entities)
//        <T> Iterable<T> save(Iterable<T> entities, IndexCoordinates index)
//        <T> Iterable<T> save(T... entities)
//        String index(IndexQuery query, IndexCoordinates index)
//        [批量新增]
//        List<String> bulkIndex(List<IndexQuery> queries, BulkOptions bulkOptions, IndexCoordinates index)

//        [查询]
//        <T> T get(String id, Class<T> clazz)
//        <T> T get(String id, Class<T> clazz, IndexCoordinates index)
//        <T> List<T> multiGet(Query query, Class<T> clazz, IndexCoordinates index)

//        [存在]？
//        boolean exists(String id, Class<?> clazz)
//        boolean exists(String id, IndexCoordinates index)
//        boolean doExists(String id, IndexCoordinates index)

//        [更新]
//        UpdateResponse update(UpdateQuery query, IndexCoordinates index)
//        [批量更新]
//        void bulkUpdate(List<UpdateQuery> queries, BulkOptions bulkOptions, IndexCoordinates index)

//        [删除]
//        String delete(Object entity)
//        String delete(Object entity, IndexCoordinates index)
//        String delete(String id, IndexCoordinates index)
//        void delete(Query query, Class<?> clazz, IndexCoordinates index)
    }
    // endregion

    // region [新增] save
    @Test
    void save1() {
        // entity
        User user = new User();
        user.setMyId(UUID.randomUUID().toString());
        user.setMyKeyword("setMyKeyword");
        user.setMyKeywordIgnoreAbove("setMyKeywordIgnoreAbove");
        user.setMyText("setMyText");
        user.setMyTextInnerKeyword("setMyTextInnerKeyword");
        user.setMyBoolean(true);
        user.setMyInteger(1);
        user.setMyLong(1L);
        user.setMyFloat(1.1F);
        user.setMyDouble(1.11D);
        user.setMyDate(LocalDateTime.now());
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("buk_user");
        //
        User user1 = elasticsearchRestTemplate.save(user);
        log.info("[新增]:{}", user1);
        User user2 = elasticsearchRestTemplate.save(user, indexCoordinates);
        log.info("[新增]:{}", user2);
    }

    @Test
    void save2() {
        // entity
        User user1 = new User();
        User user2 = new User();
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        Iterator<User> userIterator = userList.iterator();
        //
        Iterator<User> userIterator1 = elasticsearchRestTemplate.save(userIterator);
        log.info("[新增]:{}", userIterator1);
        Iterator<User> userIterator2 = elasticsearchRestTemplate.save(userIterator, indexCoordinates);
        log.info("[新增]:{}", userIterator2);
    }

    @Test
    void save3() {
        // entity
        User user1 = new User();
        User user2 = new User();
        //
        Iterable<User> userIterable = elasticsearchRestTemplate.save(user1, user2);
        log.info("[新增]:{}", userIterable);
    }
    // endregion

    // region [新增] index
    @Test
    void index() {
        // entity
        User user = new User();
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        IndexQuery indexQuery = new IndexQueryBuilder()
//                .withId()
//                .withParentId()
//                .withObject() // 文档内容
//                .withSeqNoPrimaryTerm()
//                .withSource()
//                .withVersion()
//                .build()
                .withObject(user)
                .build();
        //
        String index = elasticsearchRestTemplate.index(indexQuery, indexCoordinates);
        log.info("[新增]:{}", index);
    }
    //endregion

    // region [批量新增] bulkIndex
    @Test
    void bulkIndex() {
        // entity
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        // List<IndexQuery>
        List<IndexQuery> indexQueryList = new ArrayList<>();
        indexQueryList.add(new IndexQueryBuilder().withObject(user1).build());
        indexQueryList.add(new IndexQueryBuilder().withObject(user2).build());
        indexQueryList.add(new IndexQueryBuilder().withObject(user3).build());
        indexQueryList.add(new IndexQueryBuilder().withObject(user4).build());
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        List<String> stringList = elasticsearchRestTemplate.bulkIndex(indexQueryList, indexCoordinates);
        log.info("[新增]:{}", stringList);
    }
    //endregion

    // region [查询] get
    @Test
    void get() {
        // id
        String id = "HiYbWXQBYlFQtC6FzYMQ";
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        User user1 = elasticsearchRestTemplate.get(id, User.class);
        log.info("[查询]:{}", user1);
        User user2 = elasticsearchRestTemplate.get(id, User.class, indexCoordinates);
        log.info("[查询]:{}", user2);
    }
    // endregion

    // region [批量查询] multiGet
    @Test
    void multiGet() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        List<User> userList = elasticsearchRestTemplate
                .multiGet(QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[批量查询]:{}", userList);
    }
    // endregion

    // region [存在] exists
    @Test
    void exists() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        boolean b1 = elasticsearchRestTemplate.exists("HiYbWXQBYlFQtC6FzYMQ", User.class);
        log.info("[存在]？:{}", b1);
        boolean b2 = elasticsearchRestTemplate.exists("HiYbWXQBYlFQtC6FzYMQ", indexCoordinates);
        log.info("[存在]？:{}", b2);
    }
    // endregion

    // region [更新] update
    @Test
    void update() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        // entity
        User user = new User();
        UpdateQuery updateQuery = UpdateQuery
                .builder("HiYbWXQBYlFQtC6FzYMQ")
                .withDocument(Document.parse(JSONObject.toJSONString(user)))
                .build();
        //
        UpdateResponse updateResponse = elasticsearchRestTemplate.update(updateQuery, indexCoordinates);
        log.info("[更新]:{}", updateResponse);
    }
    // endregion

    // region [批量更新] bulkUpdate
    @Test
    void bulkUpdate() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        // List<UpdateQuery>
        List<UpdateQuery> updateQueryList = new ArrayList<>();
        updateQueryList.add(UpdateQuery.builder("HiYbWXQBYlFQtC6FzYMQ").withDocument(Document.parse(JSONObject.toJSONString(user1))).build());
        updateQueryList.add(UpdateQuery.builder("HiYbWXQBYlFQtC6FzYMQ").withDocument(Document.parse(JSONObject.toJSONString(user2))).build());
        updateQueryList.add(UpdateQuery.builder("HiYbWXQBYlFQtC6FzYMQ").withDocument(Document.parse(JSONObject.toJSONString(user3))).build());
        updateQueryList.add(UpdateQuery.builder("HiYbWXQBYlFQtC6FzYMQ").withDocument(Document.parse(JSONObject.toJSONString(user4))).build());
        //
        elasticsearchRestTemplate.bulkUpdate(updateQueryList, IndexCoordinates.of("index_1"));
        log.info("Elasticsearch - 文档测试");
    }
    //endregion

    // region [删除] delete
    @Test
    void delete1() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        // entity
        User user = new User();
        //
        String b1 = elasticsearchRestTemplate.delete(user);
        log.info("[删除]:{}", b1);
        String b2 = elasticsearchRestTemplate.delete(user, indexCoordinates);
        log.info("[删除]:{}", b2);
    }

    @Test
    void delete2() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        String b1 = elasticsearchRestTemplate.delete("HiYbWXQBYlFQtC6FzYMQ");
        log.info("Elasticsearch - 文档测试:{}", b1);
        String b2 = elasticsearchRestTemplate.delete("HiYbWXQBYlFQtC6FzYMQ", indexCoordinates);
        log.info("Elasticsearch - 文档测试:{}", b2);
    }
    // endregion
}