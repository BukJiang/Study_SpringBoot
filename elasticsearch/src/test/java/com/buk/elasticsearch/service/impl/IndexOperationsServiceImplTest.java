package com.buk.elasticsearch.service.impl;

import com.buk.elasticsearch.pojo.document.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.util.Map;

/**
 * TODO: Elasticsearch - 索引测试
 */
@Slf4j
@SpringBootTest
class IndexOperationsServiceImplTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    // region create
    @Test
    void create() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
        boolean b = indexOperations.create();
        log.info("[Elasticsearch - 索引测试]:{}", b);
    }
    // endregion

    // region exist
    @Test
    void exist() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
        boolean exists = indexOperations.exists();
        log.info("[Elasticsearch - 索引测试]:{}", exists);
    }
    // endregion

    // region get
    @Test
    void get() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
        Map<String, Object> settings = indexOperations.getSettings();
        log.info("[Elasticsearch - 索引测试]: getSettings" + settings);
        Map<String, Object> mappings = indexOperations.getMapping();
        log.info("[Elasticsearch - 索引测试]: getMapping" + mappings);
    }
    // endregion

    // region delete
    @Test
    void delete() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(User.class);
        boolean b = indexOperations.delete();
        log.info("[Elasticsearch - 索引测试]:{}", b);
    }
    // endregion
}