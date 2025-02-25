package com.buk.elasticsearch.config;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: elasticsearch测试 - 文档
 */
@Slf4j
@SpringBootTest
class DocumentTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Component
    static class User {
        private Long id;

        private String name;

        private Integer age;
    }

    /**
     * 创建文档
     *
     * @throws IOException
     */
    @Test
    void createDoc() throws IOException {
        // User对象
        User user = new User(1L, "中文名1", 11);
        // PUT /index_0/_doc/1
        IndexRequest indexRequest = new IndexRequest("index_0");
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(10L));
        indexRequest.source(JSONObject.toJSONString(user), XContentType.JSON);
        // 客户端 创建
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] createDoc: {}", indexResponse.toString());
        log.info("[elasticsearch测试-文档] createDoc: {}", indexResponse.status());
    }

    /**
     * 根据ID查询文档
     *
     * @throws IOException
     */
    @Test
    void getDocById() throws IOException {
        // GET /index_0/_doc/1
        GetRequest getRequest = new GetRequest("index_0");
        getRequest.fetchSourceContext(new FetchSourceContext(
                true,
                new String[]{"id", "name"},
                null
        ));
        getRequest.storedFields("id");
        getRequest.id("1");
        // 客户端 exists
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] getDocById: {}", exists);
        // 客户端 查询
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] getDocById:{}", getResponse.toString());
        log.info("[elasticsearch测试-文档] getDocById:{}", getResponse.getSource());
    }

    /**
     * 根据Builder查询文档
     *
     * @throws IOException
     */
    @Test
    void getDocByBuilder() throws IOException {
        // GET /index_0/_doc/_search
        SearchRequest searchRequest = new SearchRequest("index_0");
        // 查询构建器
        // 匹配
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", "中文");
        // 精确
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("name", "中");
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder
                .field("name")
                .preTags("<span style='color:red'>")
                .postTags("</span>")
        ;
        highlightBuilder
                // 是否多个高亮 true是 false否
                .requireFieldMatch(false);
        // 搜索构建器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query(matchQueryBuilder)
                .query(termQueryBuilder)
                .from(0)
                .size(10)
                .timeout(TimeValue.timeValueSeconds(3L))
                .highlighter(highlightBuilder)
        ;
        // 客户端 查询
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] getDocByBuilder: {}", searchResponse.toString());
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            log.info("[elasticsearch测试-文档] getDocByBuilder: {}", searchHit.getSourceAsMap());
        }
    }

    /**
     * 更新文档
     *
     * @throws IOException
     */
    @Test
    void updateDoc() throws IOException {
        // User对象
        User user = new User(1L, "中文名1_update", null);
        // POST /index_0/_doc/1/_update
        UpdateRequest updateRequest = new UpdateRequest("index_0", "1");
        updateRequest.timeout(TimeValue.timeValueSeconds(10L));
        updateRequest.doc(JSONObject.toJSONString(user), XContentType.JSON);
        // 客户端 更新
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] updateDoc: {}", updateResponse.getGetResult());
        log.info("[elasticsearch测试-文档] updateDoc: {}", updateResponse.getResult());
        log.info("[elasticsearch测试-文档] updateDoc: {}", updateResponse.getVersion());
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    @Test
    void deleteDoc() throws IOException {
        // DELETE /index_0/_doc/1
        DeleteRequest deleteRequest = new DeleteRequest("index_0", "1");
        deleteRequest.timeout(TimeValue.timeValueSeconds(10L));
        // 客户端 删除
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] deleteDoc: {}", deleteResponse);
        log.info("[elasticsearch测试-文档] deleteDoc: {}", deleteResponse.getResult());
        log.info("[elasticsearch测试-文档] deleteDoc:{}", deleteResponse.getVersion());
    }

    /**
     * 批量 /创建/更新/删除 文档
     */
    @Test
    void bulkCreateDoc() throws IOException {
        // User对象
        List<User> userList = new ArrayList<>();
        User user1 = new User(1L, "中文名1", 11);
        User user2 = new User(2L, "中文名2", 22);
        User user3 = new User(3L, "中文名3", 33);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        // 批量
        BulkRequest bulkRequest = new BulkRequest("index_0");
        bulkRequest.timeout(TimeValue.timeValueSeconds(100L));
        for (User user : userList) {
            // IndexRequest 创建
            // UpdateRequest 更新
            // DeleteRequest 删除
            bulkRequest.add(
                    new IndexRequest()
                            .id(String.valueOf(user.getId()))
                            .source(JSONObject.toJSONString(user), XContentType.JSON)
            );
        }
        // 客户端 操作
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("[elasticsearch测试-文档] bulkCreateDoc: {}", bulkResponse.status());
        log.info("[elasticsearch测试-文档] bulkCreateDoc: {}", bulkResponse.hasFailures());
    }
}