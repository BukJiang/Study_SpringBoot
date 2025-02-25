package com.buk.utils.lambda;

import com.buk.utils.lambda.pojo.dto.User;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TODO: Lambda - Map
 * <p>
 * Map: getOrDefault()、forEach()、replaceAll()、putIfAbsent()、remove()、replace()、computeIfAbsent()、computeIfPresent()、compute()、merge()
 */
@Slf4j
@SpringBootTest
class MapTest {

    private static final Map<Integer, Integer> integerMap = Maps.newHashMap();

    private static final Map<String, String> stringMap = Maps.newHashMap();

    private static final Map<String, User> userMap = Maps.newHashMap();

    static {
        Integer integer;
        String string = "";
        User user;
        for (int i = 1; i <= 10; i++) {
            integer = i;
            integerMap.put(integer, integer);
            string = string + i;
            stringMap.put(string, string);
            user = new User();
            userMap.put(user.getName(), user);
        }
    }

    /**
     * V getOrDefault(Object key, V defaultValue)
     * 作用: 若key所属映射存在，则返回value，否则返回defaultValue
     */
    @Test
    void getOrDefault() {
        //
        log.info("[getOrDefault] integerMap{}", integerMap);
        Integer hasValue = integerMap.getOrDefault(1, 11);
        log.info("[getOrDefault] hasValue:{}", hasValue);
        Integer defaultValue = integerMap.getOrDefault(11, 111);
        log.info("[getOrDefault] defaultValue:{}", defaultValue);
    }

    /**
     * void forEach(BiConsumer<? super K, ? super V> action)
     * 作用: 对Map中的映射执行action指定的操作
     */
    @Test
    void forEach() {
        stringMap.forEach((key, value) -> {
            log.info("[forEach] key:{}, value:{}", key, value);
        });
    }

    /**
     * void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
     * 作用: 对Map中的映射执行function的操作，并将Value赋值为操作结果
     */
    @Test
    void replaceAll() {
        stringMap.replaceAll((key, value) -> {
            return "<" + value + ">";
        });
        log.info("[replaceAll] stringMap{}", stringMap);
    }

    /**
     * V putIfAbsent(K key, V value)
     * 作用: 若该key不存在映射或映射值为null，则将key与value关联并返回null，否则返回当前值
     */
    @Test
    void putIfAbsent() {
        Integer integer1 = integerMap.putIfAbsent(10, 11);
        log.info("[putIfAbsent] integer:{}, integerMap{}", integer1, integerMap);
        Integer integer2 = integerMap.putIfAbsent(11, 11);
        log.info("[putIfAbsent] integer:{}, integerMap{}", integer2, integerMap);
    }

    /**
     * V replace(K key, V value)
     * 作用: 若当前Map中key的映射存在，则使用value去替换原来的值，否则什么也不做
     * <p>
     * boolean replace(K key, V oldValue, V newValue)
     * 作用: 若当前Map中key的映射存在且等于oldValue，则使用newValue去替换原来的值，否则什么也不做
     */
    @Test
    void replace() {
        Integer replace1 = integerMap.replace(10, 11);
        log.info("[replace] replace1:{}, integerMap{}", replace1, integerMap);

        boolean replace2 = stringMap.replace("1", "11", "111");
        log.info("[replace] replace2:{}, stringMap{}", replace2, stringMap);
        boolean replace3 = stringMap.replace("1", "1", "11");
        log.info("[replace] replace3:{}, stringMap{}", replace3, stringMap);
    }

    /**
     * V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
     * 作用: 若当前Map中不存在key值的映射或映射值为null，则调用mappingFunction，并在mappingFunction执行结果非null时，将结果跟key关联
     */
    @Test
    void computeIfAbsent() {
        Map<Integer, Set<String>> map = new HashMap<>();
        // Java7及以前的实现方式
        if (map.containsKey(1)) {
            map.get(1).add("one");
        } else {
            Set<String> valueSet = new HashSet<>();
            valueSet.add("one");
            map.put(1, valueSet);
        }
        // Java8的实现方式
        Set<String> set = map.computeIfAbsent(1, value -> new HashSet<>());
        set.add("one");
    }

    /**
     * V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
     * 作用:
     * 1. 若当前Map中存在key值的映射且非null，则调用remappingFunction。若remappingFunction执行结果不为null，则将结果跟key关联，否则则删除key的映射
     * 2. 若当前Map中不存在key值的映射或映射值为null，则不调用则调用remappingFunction
     */
    @Test
    void computeIfPresent() {
        // 1
        Integer integer1 = integerMap.computeIfPresent(1, (key, value) -> {
            log.info("[computeIfPresent] integer1 into");
            return 11;
        });
        log.info("[computeIfPresent] integer1:{}, integerMap{}", integer1, integerMap);
        // 2
        Integer integer2 = integerMap.computeIfPresent(1, (key, value) -> {
            log.info("[computeIfPresent] integer2 into");
            return null;
        });
        log.info("[computeIfPresent] integer2:{}, integerMap{}", integer2, integerMap);
        // 3
        Integer integer3 = integerMap.computeIfPresent(11, (key, value) -> {
            log.info("[computeIfPresent] integer3 into");
            return 11;
        });
        log.info("[computeIfPresent] integer3:{}, integerMap{}", integer3, integerMap);
        // 4
        Integer integer4 = integerMap.computeIfPresent(11, (key, value) -> {
            log.info("[computeIfPresent] integer4 into");
            return null;
        });
        log.info("[computeIfPresent] integer4:{}, integerMap{}", integer4, integerMap);
    }

    /**
     * V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
     * 作用: 不论当前Map中key值是否存在映射以及映射值是否非null，都调用remappingFunction。若remappingFunction执行结果不为null，则将结果跟key关联，否则删除key的映射
     */
    @Test
    void compute() {
        // 1
        Integer integer1 = integerMap.compute(1, (key, value) -> {
            log.info("[compute] integer1 into");
            return 11;
        });
        log.info("[compute] integer1:{}, integerMap{}", integer1, integerMap);
        // 2
        Integer integer2 = integerMap.compute(1, (key, value) -> {
            log.info("[compute] integer2 into");
            return null;
        });
        log.info("[compute] integer2:{}, integerMap{}", integer2, integerMap);
        // 3
        Integer integer3 = integerMap.compute(11, (key, value) -> {
            log.info("[compute] integer3 into");
            return 11;
        });
        log.info("[compute] integer3:{}, integerMap{}", integer3, integerMap);
        // 4
        Integer integer4 = integerMap.compute(11, (key, value) -> {
            log.info("[compute] integer4 into");
            return null;
        });
        log.info("[compute] integer4:{}, integerMap{}", integer4, integerMap);
    }

    /**
     * V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
     * 作用:
     * 1. 如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上
     * 2. 否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射
     */
    @Test
    void merge() {
        // 1
        Integer merge1 = integerMap.merge(1, 11, (key, value) -> {
            log.info("[compute] merge1 into");
            return 111;
        });
        log.info("[merge] merge1:{}, integerMap{}", merge1, integerMap);
        // 2
        Integer merge2 = integerMap.merge(1, 11, (key, value) -> {
            log.info("[compute] merge2 into");
            return null;
        });
        log.info("[merge] merge2:{}, integerMap{}", merge2, integerMap);
        // 3
        Integer merge3 = integerMap.merge(11, 11, (key, value) -> {
            log.info("[compute] merge3 into");
            return 111;
        });
        log.info("[merge] merge3:{}, integerMap{}", merge3, integerMap);
        // 4
        Integer merge4 = integerMap.merge(11, 11, (key, value) -> {
            log.info("[compute] merge4 into");
            return null;
        });
        log.info("[merge] merge4:{}, integerMap{}", merge4, integerMap);
    }
}
