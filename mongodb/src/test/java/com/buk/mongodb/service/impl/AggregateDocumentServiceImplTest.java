package com.buk.mongodb.service.impl;

import com.buk.mongodb.pojo.entity.testdb.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Map;

/**
 * TODO: MongoDB - (聚合)文档测试
 * <p>
 * 【管道】
 * group 将集合中的文档分组，可用于统计结果。
 * sort 将输入文档排序后输出。
 * match 用于过滤数据，只输出符合条件的文档，$match使用MongoDB的标准查询操作。
 * limit 用来限制MongoDB聚合管道返回的文档数。
 * skip 在聚合管道中跳过指定数量的文档，并返回余下的文档。
 * project 可以从文档中选择想要的字段，和不想要的字段（指定的字段可以是来自输入文档或新计算字段的现有字段，
 * 也可以通过管道表达式进行一些复杂的操作，例如数学操作，日期操作，字符串操作，逻辑操作。
 * unwind 将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
 * <p>
 * 【方法】
 * count 统计各个组的文档数量
 * max 统计各个组文档某字段最大值
 * min 统计各个组文档某字段最小值
 * sum 统计各个组文档某字段值合计
 * avg 统计各个组文档某字段值平均值
 * first 统计各个组文档某字段值第一个值
 * last 统计各个组文档某字段值第最后一个值
 * push 以数组形式列出某字段的全部值
 */
@Slf4j
@SpringBootTest
class AggregateDocumentServiceImplTest {

    private static final String COLLECTION_NAME_1 = "users1";
    private static final String COLLECTION_NAME_2 = "users2";
    private static final String COLLECTION_NAME_3 = "users3";

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 使用管道操作符 $group 结合【方法】进行聚合统计
     */
    @Test
    void aggregate1() {
        // 使用管道操作符 $group 进行分组，然后统计各个组的文档数量
        AggregationOperation aggregationOperation = Aggregation
                .group("age").count().as("ageCount");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(aggregationOperation);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用管道操作符 $group 结合表达式操作符 $first 获取每个组的包含某字段的文档的第一条数据
     */
    @Test
    void aggregate2() {
        // 先对数据进行排序，然后使用管道操作符 $group 进行分组，最后统计各个组文档某字段值第一个值
        AggregationOperation sort = Aggregation
                .sort(Sort.by("age").ascending());
        AggregationOperation group = Aggregation
                .group("sex").first("age").as("ageFirst");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(sort, group);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用管道操作符 $group 结合表达式操作符 $push 获取某字段列表
     */
    @Test
    void aggregate3() {
        // 先对数据进行排序，然后使用管道操作符 $group 进行分组，然后以数组形式列出某字段的全部值
        AggregationOperation push = Aggregation
                .group("sex").push("age").as("ageList");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(push);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用 $group 和 $match 聚合，先使用 $match 过滤文档，然后再使用 $group 进行分组
     */
    @Test
    void aggregate4() {
        // 设置聚合条件，先使用 $match 过滤岁数大于 100 的用户，然后按性别分组，统计每组用户age最高值
        AggregationOperation match = Aggregation
                .match(Criteria.where("age").lt(100));
        AggregationOperation group = Aggregation
                .group("sex").max("age").as("ageMax");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(match, group);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用 $group 和 $limit 聚合，先使用 $group 进行分组，然后再使用 $limit 限制一定数目文档
     */
    @Test
    void aggregate5() {
        // 设置聚合条件，先按sex分组，然后求每组用户的age总数、最大值、最小值、平均值，限制只能显示1条
        AggregationOperation group = Aggregation
                .group("sex")
                .sum("age").as("ageSum")
                .max("age").as("ageMax")
                .min("age").as("ageMin")
                .avg("age").as("ageAvg");
        AggregationOperation limit = Aggregation
                .limit(1L);
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(group, limit);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用 $group 和 $project 聚合,先使用 $group 进行分组，然后再使用 $project 限制显示的字段
     */
    @Test
    void aggregate6() {
        // 设置聚合条件,按sex分组，然后求每组用户age最大值、最小值，然后使用 $project 限制值显示 ageMin 字段
        AggregationOperation group = Aggregation
                .group("sex")
                .max("age").as("ageMax")
                .min("age").as("ageMin");
        AggregationOperation project = Aggregation
                .project("ageMin");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(group, project);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }

    /**
     * 使用 $group 和 $unwind 聚合,先使用 $project 进行分组，然后再使用 $unwind 拆分文档中的数组为一条新文档记录
     */
    @Test
    void aggregate7() {
        // 设置聚合条件，设置显示`name`、`sex`、`age`字段，然后将结果中的多条文档按 age 字段进行拆分
        AggregationOperation project = Aggregation
                .project("name", "sex", "age");
        AggregationOperation unwind = Aggregation
                .unwind("age");
        // 将操作加入到聚合对象中
        Aggregation aggregation = Aggregation.newAggregation(project, unwind);
        // 执行聚合查询
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, User.class, Map.class);
        for (Map result : aggregationResults.getMappedResults()) {
            log.info("[MongoDB - (聚合)文档测试]: {}", result);
        }
    }
}