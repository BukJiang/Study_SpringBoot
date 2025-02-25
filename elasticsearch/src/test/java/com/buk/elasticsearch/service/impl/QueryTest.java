package com.buk.elasticsearch.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;

/**
 * TODO: Elasticsearch - Query测试
 */
@SpringBootTest
class QueryTest {

    // region 简单查询测试
    public static Query simpleQueryTest() {
        // Criteria
        Criteria criteria = new Criteria("fieldName").is("value");
        // CriteriaQuery
        return new CriteriaQuery(criteria);
    }
    // endregion

    // region StringQuery
    @Test
    void stringQuery() {
        // Pageable
        PageRequest pageRequest = PageRequest.of(0, 100);

        // source
        String source = "{\"match\": {\"name\": {\"query\": \"nameValue\"}}}";
        // Sort
        Sort sort = Sort.by("fieldName").ascending();
        // StringQuery
        StringQuery stringQuery1 = new StringQuery(source);
        StringQuery stringQuery2 = new StringQuery(source, pageRequest);
        StringQuery stringQuery3 = new StringQuery(source, pageRequest, sort);
    }
    // endregion

    // region CriteriaQuery
    @Test
    void CriteriaQuery() {
        // Pageable
        PageRequest pageRequest = PageRequest.of(0, 100);
        // Criteria
        Criteria criteria = new Criteria("nameText").is("中");
        // CriteriaQuery
        CriteriaQuery criteriaQuery1 = new CriteriaQuery(criteria);
        CriteriaQuery criteriaQuery2 = new CriteriaQuery(criteria, pageRequest);
    }
    // endregion

    // region NativeSearchQuery
    @Test
    void nativeSearchQuery() {
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
//        nativeSearchQueryBuilder
//                .withQuery(QueryBuilder queryBuilder)
//
//                .withFilter(QueryBuilder filterBuilder)
//                .withSourceFilter(SourceFilter sourceFilter)
//
//                .withFields(String...fields)
//                .withScriptField(ScriptField scriptField)
//                .withCollapseField(String collapseField)
//                .withHighlightFields(HighlightBuilder.Field...highlightFields)
//                .withHighlightBuilder(HighlightBuilder highlightBuilder)
//
//                .withSort(SortBuilder sortBuilder)
//
//                .withMinScore(float minScore)
//                .withTrackScores(boolean trackScores)
//
//                .addAggregation(AbstractAggregationBuilder aggregationBuilder)
//
//                .withIndicesBoost(List<IndexBoost> indicesBoost)
//
//                .withPageable(Pageable pageable)
//
//                .withIds(Collection<String> ids)
//
//                .withRoute(String route)
//
//                .withSearchType(SearchType searchType)
//
//                .withIndicesOptions(IndicesOptions indicesOptions)
//
//                .withPreference(String preference)
//
//                .build();
    }
    // endregion
}
