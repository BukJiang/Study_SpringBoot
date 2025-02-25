package com.buk.utils.lambda;

import com.buk.utils.lambda.pojo.dto.User;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO: Lambda - Collectors
 */
@Slf4j
@SpringBootTest
class CollectorsTest {

    private static List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private static List<String> stringList = Lists.newArrayList("1", "22", "333", "4444", "55555", "666666", "7777777", "88888888", "999999999");

    private static List<User> userList = Lists.newArrayList(new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User());

    /**
     * 将流中的元素放置到集合中返回
     */
    @Test
    void toCollection() {
        Collection<Integer> toCollection = integerList.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toCollection(LinkedList::new));
        log.info("[toCollection]-{}", toCollection);
    }

    /**
     * 将流中的元素放置到列表集合中返回，默认为ArrayList
     */
    @Test
    void toList() {
        List<Integer> toList = integerList.stream()
                .map(integer -> integer * integer)
                .collect(Collectors.toList());
        log.info("[toList]-{}", toList);
    }

    /**
     * 将流中的元素放置到无序Set集合中返回，默认为HashSet
     */
    @Test
    void toSet() {
        Set<Integer> toSet = integerList.stream()
                .map(integer -> 1)
                .collect(Collectors.toSet());
        log.info("[toSet]-{}", toSet);
    }

    /**
     * 根据生成器生成的键和值保存到一个map中返回，键和值的生成都依赖于元素，可以指定出现重复键时的处理方案和保存结果的map
     */
    @Test
    void toMap() {
        Map<String, String> toMap1 = integerList.stream().collect(Collectors.toMap(key -> "key:" + key, value -> "value:" + value));
        Map<String, String> toMap2 = integerList.stream().collect(Collectors.toMap(key -> "key:" + key, value -> "value:" + value, (a, b) -> b));
        Map<String, String> toMap3 = integerList.stream().collect(Collectors.toMap(key -> "key:" + key, value -> "value:" + value, (a, b) -> b, HashMap::new));
        log.info("[toMap]-{}", toMap1);
        log.info("[toMap]-{}", toMap2);
        log.info("[toMap]-{}", toMap3);
    }

    /**
     * 将流中的元素以字符序列的方式拼接到一起，允许指定连接符，结果的前后缀
     */
    @Test
    void joining() {
        String joining1 = stringList.stream().collect(Collectors.joining());
        String joining2 = stringList.stream().collect(Collectors.joining(","));
        String joining3 = stringList.stream().collect(Collectors.joining(",", "{", "}"));
        log.info("[joining]-{}", joining1);
        log.info("[joining]-{}", joining2);
        log.info("[joining]-{}", joining3);
    }

    /**
     * 将流中的元素进行映射（即类型转换），最终将新元素以给定的Collector进行归纳
     */
    @Test
    void mapping() {
        List<Integer> mapping1 = integerList.stream().collect(Collectors.mapping(integer -> integer * integer, Collectors.toList()));
        List<Long> mapping2 = integerList.stream().collect(Collectors.mapping(Long::valueOf, Collectors.toList()));
        log.info("[mapping]-{}", mapping1);
        log.info("[mapping]-{}", mapping2);
    }

    /**
     * 将流中的元素进行归纳动作结束之后，对归纳的结果进行再处理
     */
    @Test
    void collectingAndThen() {
        Integer collectingAndThen = integerList.stream()
                .map(integer -> 1)
                .collect(Collectors.collectingAndThen(Collectors.toList(), toList -> toList.size()));
        log.info("[collectingAndThen]-{}", collectingAndThen);
    }

    /**
     * 用于计数
     */
    @Test
    void counting() {
        Long counting = integerList.stream()
                .skip(3)
                .limit(3)
                .collect(Collectors.counting());
        log.info("[counting]-{}", counting);
    }

    /**
     * 获取最小的Optional结果
     */
    @Test
    void minBy() {
        Optional<Integer> minBy = integerList.stream().collect(Collectors.minBy((a, b) -> a - b));
        log.info("[minBy]-{}", minBy.get());
    }

    /**
     * 获取最大的Optional结果
     */
    @Test
    void maxBy() {
        Optional<Integer> maxBy = integerList.stream().collect(Collectors.maxBy((a, b) -> a - b));
        log.info("[maxBy]-{}", maxBy.get());
    }

    /**
     * 求和
     */
    @Test
    void summing() {
        Integer summingInt = integerList.stream().collect(Collectors.summingInt(Integer::valueOf));
        Long summingLong = integerList.stream().collect(Collectors.summingLong(Long::valueOf));
        Double summingDouble = integerList.stream().collect(Collectors.summingDouble(Double::valueOf));
        log.info("[summingInt]-{}", summingInt);
        log.info("[summingLong]-{}", summingLong);
        log.info("[summingDouble]-{}", summingDouble);
    }

    /**
     * 平均值
     */
    @Test
    void averaging() {
        double averagingInt = integerList.stream().collect(Collectors.averagingInt(Integer::valueOf));
        double averagingLong = integerList.stream().collect(Collectors.averagingLong(Long::valueOf));
        double averagingDouble = integerList.stream().collect(Collectors.averagingDouble(Double::valueOf));
        log.info("[averagingInt]-{}", averagingInt);
        log.info("[averagingLong]-{}", averagingLong);
        log.info("[averagingDouble]-{}", averagingDouble);
    }

    /**
     * 聚合
     */
    @Test
    void reducing() {
        Optional<Integer> reducing1 = integerList.stream().collect(Collectors.reducing((o1, o2) -> o1 + o2));
        Integer reducing2 = integerList.stream().collect(Collectors.reducing(0, (o1, o2) -> o1 + o2));
        Integer reducing3 = integerList.stream().collect(Collectors.reducing(0, integer -> integer * integer, (o1, o2) -> o1 + o2));
        log.info("[reducing]-{}", reducing1);
        log.info("[reducing]-{}", reducing2);
        log.info("[reducing]-{}", reducing3);
    }

    /**
     * 分组
     */
    @Test
    void groupingBy() {
        Map<Integer, List<Integer>> groupingBy1 = integerList.stream().collect(Collectors.groupingBy(integer -> integer % 2));
        Map<Integer, List<Integer>> groupingBy2 = integerList.stream().collect(Collectors.groupingBy(integer -> integer % 2, Collectors.toList()));
        Map<Integer, List<Integer>> groupingBy3 = integerList.stream().collect(Collectors.groupingBy(integer -> integer % 2, HashMap::new, Collectors.toList()));
        log.info("[groupingBy]-{}", groupingBy1);
        log.info("[groupingBy]-{}", groupingBy2);
        log.info("[groupingBy]-{}", groupingBy3);
    }

    /**
     * 将流中的元素按照校验规则的结果分为两个部分放到map中返回，map的键是Boolean类型，值为元素的列表List
     */
    @Test
    void partitioningBy() {
        Map<Boolean, List<Integer>> partitioningBy1 = integerList.stream().collect(Collectors.partitioningBy(integer -> integer > 5));
        Map<Boolean, Set<Integer>> partitioningBy2 = integerList.stream().collect(Collectors.partitioningBy(integer -> integer > 5, Collectors.toSet()));
        log.info("[partitioningBy]-{}", partitioningBy1);
        log.info("[partitioningBy]-{}", partitioningBy2);
    }

    /**
     * 汇总 {count=xxx, sum=xxx, min=xxx, average=xxx, max=xxx}
     */
    @Test
    void summarizing() {
        IntSummaryStatistics summarizingInt = stringList.stream().collect(Collectors.summarizingInt(String::length));
        LongSummaryStatistics summarizingLong = stringList.stream().collect(Collectors.summarizingLong(Long::valueOf));
        DoubleSummaryStatistics summarizingDouble = stringList.stream().collect(Collectors.summarizingDouble(Double::valueOf));
        log.info("[summarizing]-{}", summarizingInt);
        log.info("[summarizing]-{}", summarizingLong);
        log.info("[summarizing]-{}", summarizingDouble);
    }
}