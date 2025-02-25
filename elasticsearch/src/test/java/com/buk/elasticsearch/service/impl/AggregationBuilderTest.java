package com.buk.elasticsearch.service.impl;

import com.buk.elasticsearch.pojo.document.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

/**
 * TODO: Elasticsearch - AggregationBuilder测试
 **/
@Slf4j
@SpringBootTest
class AggregationBuilderTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void AggregationBuilders_1() {
        // term查询
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("FILED_NAME", "FILED_VALUE");
        // bool查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .filter(termQueryBuilder);
        String boolQuerySql = boolQueryBuilder.toString();
        log.info("[AggregationBuilders_1] boolQuerySql:{}", boolQuerySql);
        // max聚合
        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders
                .max("ALIAS_FILED_NAME").field("FILED_NAME");
        String aggsQuerySql = maxAggregationBuilder.toString();
        log.info("[AggregationBuilders_1] aggsQuerySql:{}", aggsQuerySql);
        // 查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder
                .withQuery(boolQueryBuilder)
                .addAggregation(maxAggregationBuilder)
                .build();
    }

    @Test
    void AggregationBuilders_2() {
        // term查询
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("FILED_NAME", "FILED_VALUE");
        // bool查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .filter(termQueryBuilder);
        // aggregation查询
        ValueCountAggregationBuilder valueCountAggregationBuilder = AggregationBuilders
                .count("ALIAS_FILED_NAME").field("FILED_NAME");
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
                .terms("ALIAS_FILED_NAME").field("FILED_NAME")
                .size(1000)
                .subAggregation(valueCountAggregationBuilder);
        // 查询语句
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQueryBuilder)
                .addAggregation(termsAggregationBuilder)
                .build();
        SearchHits<User> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, User.class);
        // 聚合数据
        Aggregations aggregations = searchHits.getAggregations();
    }

    /**
     * 时间分组
     */
    @Test
    void groupByDateTime() {
        // term查询
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("FILED_NAME", "FILED_VALUE");
        // bool查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
                .filter(termQueryBuilder);
        // aggregation查询
        ValueCountAggregationBuilder valueCountAggregationBuilder = AggregationBuilders
                .count("ALIAS_FILED_NAME").field("FILED_NAME");
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder = AggregationBuilders
                .dateHistogram("ALIAS_FILED_NAME").field("FILED_NAME")
                .fixedInterval(DateHistogramInterval.DAY)
                .subAggregation(valueCountAggregationBuilder);
        // 查询语句
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQueryBuilder)
                .addAggregation(dateHistogramAggregationBuilder)
                .build();
        SearchHits<User> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, User.class);
        // 聚合数据
        Aggregations aggregations = searchHits.getAggregations();
    }
}
