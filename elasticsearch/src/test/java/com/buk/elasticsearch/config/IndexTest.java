package com.buk.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * TODO: elasticsearch测试 - 索引
 */
@Slf4j
@SpringBootTest
class IndexTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     */
    @Test
    void createIndex() throws IOException {
        // 1. 创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("index_0");

        // 2.1 索引客户端 indicesClient
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 2.2 索引客户端 创建
        CreateIndexResponse createIndexResponse = indicesClient
                .create(createIndexRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-索引] createIndex:{}", createIndexResponse.index());
    }

    /**
     * 获取索引
     */
    @Test
    void getIndex() throws IOException {
        // 1. 获取索引请求
        GetIndexRequest getIndexRequest = new GetIndexRequest("index_0");

        // 2.1 索引客户端 indicesClient
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 2.2 索引客户端 exists
        boolean exists = indicesClient.exists(getIndexRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-索引] getIndex:{}", exists);
        // 2.3 索引客户端 获取
        GetIndexResponse getIndexResponse = indicesClient.get(getIndexRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-索引] getIndex:{}", getIndexResponse);
    }

    /**
     * 删除索引
     */
    @Test
    void deleteIndex() throws IOException {
        // 1. 删除索引请求
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("index_0");

        // 2.1 索引客户端 indicesClient
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 2.2 索引客户端 删除
        AcknowledgedResponse acknowledgedResponse = indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-索引] deleteIndex:{}", acknowledgedResponse.isAcknowledged());
    }
}