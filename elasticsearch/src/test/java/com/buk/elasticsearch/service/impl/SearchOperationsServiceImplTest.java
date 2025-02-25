package com.buk.elasticsearch.service.impl;

import com.buk.elasticsearch.pojo.document.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsIterator;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.util.CloseableIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Elasticsearch - 搜索操作测试
 */
@Slf4j
@SpringBootTest
class SearchOperationsServiceImplTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    void SearchOperations() {
//        long count(Query query, Class<?> clazz)
//        long count(Query query, @Nullable Class<?> clazz, IndexCoordinates index)

//        CloseableIterator<T> stream(Query query, Class<T> clazz, IndexCoordinates index)

//        SearchHitsIterator<T> searchForStream(Query query, Class<T> clazz)
//        SearchHitsIterator<T> searchForStream(Query query, Class<T> clazz, IndexCoordinates index)

//        SearchHits<T> search(MoreLikeThisQuery query, Class<T> clazz)
//        SearchHits<T> search(MoreLikeThisQuery query, Class<T> clazz, IndexCoordinates index)

//        SearchHits<T> search(Query query, Class<T> clazz)
//        SearchHits<T> search(Query query, Class<T> clazz, IndexCoordinates index)

//        List<SearchHits<T>> multiSearch(List<? extends Query> queries, Class<T> clazz, IndexCoordinates index)
//        List<SearchHits<?>> multiSearch(List<? extends Query> queries, List<Class<?>> classes, IndexCoordinates index)

//        SearchScrollHits<T> searchScrollStart(long scrollTimeInMillis, Query query, Class<T> clazz, IndexCoordinates index)
//        SearchScrollHits<T> searchScrollContinue(@Nullable String scrollId, long scrollTimeInMillis, Class<T> clazz, IndexCoordinates index)

//        void searchScrollClear(List<String> scrollIds)

//        SearchResponse suggest(SuggestBuilder suggestion, IndexCoordinates index)

//        MultiSearchResponse.Item[] getMultiSearchResult(MultiSearchRequest request)
    }


    // region [计数] count
    @Test
    void count() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        long count1 = elasticsearchRestTemplate.count(QueryTest.simpleQueryTest(), indexCoordinates);
        log.info("[计数]: {}", count1);
        long count2 = elasticsearchRestTemplate.count(QueryTest.simpleQueryTest(), User.class);
        log.info("[计数]: {}", count2);
        long count3 = elasticsearchRestTemplate.count(QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[计数]: {}", count3);
    }
    // endregion

    // region [流] stream
    @Test
    void stream() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        CloseableIterator<User> stream = elasticsearchRestTemplate.stream(QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[流]: {}", stream);
    }
    // endregion

    // region [流] searchForStream
    @Test
    void searchForStream() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        SearchHitsIterator<User> userSearchHitsIterator1 = elasticsearchRestTemplate
                .searchForStream(QueryTest.simpleQueryTest(), User.class);
        log.info("[流]: {}", userSearchHitsIterator1);
        SearchHitsIterator<User> userSearchHitsIterator2 = elasticsearchRestTemplate
                .searchForStream(QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[流]: {}", userSearchHitsIterator2);
    }
    // endregion

    // region [搜索] search
    @Test
    void search() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        SearchHits<User> search1 = elasticsearchRestTemplate
                .search(QueryTest.simpleQueryTest(), User.class);
        log.info("[搜索]: {}", search1);
        SearchHits<User> search2 = elasticsearchRestTemplate
                .search(QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[搜索]: {}", search2);
    }
    // endregion

    // region [批量搜索] multiSearch
    @Test
    void multiSearch() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        List<Query> queries = new ArrayList<>();
        queries.add(QueryTest.simpleQueryTest());
        //
        List<Class<?>> classes = new ArrayList<>();
        classes.add(User.class);
        //
        List<SearchHits<User>> searchHits1 = elasticsearchRestTemplate.multiSearch(queries, User.class, indexCoordinates);
        log.info("[批量搜索]: {}", searchHits1);
        List<SearchHits<?>> searchHits2 = elasticsearchRestTemplate.multiSearch(queries, classes, indexCoordinates);
        log.info("[批量搜索]: {}", searchHits2);
    }
    // endregion

    // region [滚动搜索]
    @Test
    void searchScrollStart() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        SearchScrollHits<User> searchHits = elasticsearchRestTemplate
                .searchScrollStart(100L, QueryTest.simpleQueryTest(), User.class, indexCoordinates);
        log.info("[滚动搜索]: {}", searchHits);
    }

    @Test
    void searchScrollContinue() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        //
        SearchScrollHits<User> searchHits = elasticsearchRestTemplate
                .searchScrollContinue("scrollId", 100L, User.class, indexCoordinates);
        log.info("[滚动搜索]: {}", searchHits);
    }

    @Test
    void searchScrollClear() {
        //
        List<String> scrollIds = new ArrayList<>();
        scrollIds.add("scrollId");
        //
        elasticsearchRestTemplate.searchScrollClear(scrollIds);
    }
    // endregion

    // region [建议] suggest
    @Test
    void suggest() {
        // IndexCoordinates
        IndexCoordinates indexCoordinates = IndexCoordinates.of("index_1");
        // SuggestBuilder
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        //
        SearchResponse suggest = elasticsearchRestTemplate.suggest(suggestBuilder, indexCoordinates);
        log.info("[建议]: {}", suggest);
    }
    // endregion
}