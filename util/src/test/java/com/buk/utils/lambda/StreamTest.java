package com.buk.utils.lambda;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Lambda - Stream
 * <p>
 * 1. 无存储：stream不是一种数据结构，只是某种数据源的一个视图，数据源可以是一个数组、Java容器或I/O channel等。
 * 2. 为函数式编程而生：对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，而是产生一个不包含被过滤元素的新stream。
 * 3. 惰式执行：stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
 * 4. 可消费性：stream只能被“消费”一次，遍历过就会失效。像容器的迭代器，想再次遍历必须重新生成。
 * <p>
 * 操作类型：
 * 1. 中间操作(intermediate operations)：惰式执行，调用中间操作只会生成一个标记了该操作的新stream，仅此而已。
 * --- sequential()、parallel()、unordered()、filter()、map()、flatMap()、distinct()、sorted()、peek()、limit()、skip()、concat()
 * 2. 结束操作(terminal operations)：触发实际计算，会将所有中间操作积攒的操作以pipeline的方式执行，达到减少迭代次数。计算完成后stream会失效。
 * --- forEach()、forEachOrdered()、toArray()、reduce()、min()、max()、count()、collect()、anyMatch()、allMatch()、noneMatch()、findFirst()、findAny()
 */
@Slf4j
@SpringBootTest
class StreamTest {

    private static List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private static List<String> stringList = Lists.newArrayList("", "1", "22", "333", "4444", "55555", "666666", "7777777", "88888888", "999999999");

    /**
     * 流类型
     */
    @Test
    void stream() {
        // 顺序流
        Stream<Integer> sequential = integerList.stream().sequential();
        // 并行流
        Stream<Integer> parallel = integerList.stream().parallel();
        // 无序流
        Stream<Integer> unordered = integerList.stream().unordered();
        // 去重流
        Stream<Integer> distinct = integerList.stream().distinct();
    }

    /**
     * Stream<T> filter(Predicate<? super T> predicate)
     * 作用: 返回只包含满足predicate条件元素的Stream
     */
    @Test
    void filter() {
        log.info("[filter] integerList{}", integerList);
        //
        Stream<Integer> stream1 = integerList.stream();
        Stream<Integer> integerStream1 = stream1.filter(integer -> {
            return integer > 3 && integer < 7;
        });
        integerStream1.forEach(integer -> {
            log.info("[filter] integer1:{}", integer);
        });
        //
        Stream<Integer> stream2 = integerList.stream();
        Stream<Integer> integerStream2 = stream2
                .filter(integer -> integer > 3)
                .filter(integer -> integer < 7);
        integerStream2.forEach(integer -> {
            log.info("[filter] integer2:{}", integer);
        });
    }

    /**
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper)
     * 作用: 对当前所有元素执行mapper操作
     */
    @Test
    void map() {
        log.info("[map] integerList{}", integerList);
        Stream<Integer> stream = integerList.stream();
        Stream<Integer> integerStream = stream.map(integer -> integer++);
        integerStream.forEach(integer -> {
            log.info("[map] integer:{}", integer);
        });
    }

    /**
     * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
     * 作用: 对每个元素执行mapper操作，并将mapper返回的元素组成一个新的Stream
     */
    @Test
    void flatMap() {
        Stream<List<Integer>> stream = Stream.of(Collections.singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        stream
                .flatMap(list -> list.stream())
                .forEach(integer -> {
                    log.info("[flatMap] integer:{}", integer);
                });
    }

    /**
     * 自然排序顺序: Stream<T> sorted()
     * 自定义比较器排序: Stream<T> sorted(Comparator<? super T> comparator)
     */
    @Test
    void sorted() {
        log.info("[sorted] integerList{}", integerList);
        // 1
        Stream<Integer> sorted1 = integerList.stream()
                .sorted();
        sorted1.forEach(integer -> {
            log.info("[sorted] integer1:{}", integer);
        });
        // 2
        Stream<Integer> sorted2 = integerList.stream()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
        sorted2.forEach(integer -> {
            log.info("[sorted] integer2:{}", integer);
        });
    }

    /**
     * Stream<T> peek(Consumer<? super T> action)
     * 作用: 返回一个由该流的元素组成的流，并从结果流中消耗元素时对每个元素执行提供的操作
     */
    @Test
    void peek() {
        integerList.stream()
                .peek(integer -> {
                    log.info("[peek] integer:{}", integer);
                })
                .forEach(integer -> {
                    log.info("[peek] integer:{}", integer);
                });
    }

    /**
     * Stream<T> limit(long maxSize)
     * 作用: 返回被截断为maxSize的流
     */
    @Test
    void limit() {
        integerList.stream()
                .limit(3)
                .forEach(integer -> {
                    log.info("[limit] integer:{}", integer);
                });
    }

    /**
     * Stream<T> skip(long n)
     * 作用: 返回跳过n个元素的流
     */
    @Test
    void skip() {
        integerList.stream()
                .skip(7)
                .forEach(integer -> {
                    log.info("[skip] integer:{}", integer);
                });
    }

    /**
     * <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
     * 作用： 创建一个延迟连接的流，其元素是第一个流的所有元素，后跟第二个流的所有元素。
     * 如果两个输入流都是有序的，则生成的流是有序的；如果其中一个输入流是并行的，则生成的流是并行的。
     * 当结果流关闭时，将调用两个输入流的关闭处理程序。
     */
    @Test
    void concat() {
        Stream<Integer> concat = Stream.concat(integerList.stream(), integerList.stream());
        concat.forEach(integer -> {
            log.info("[concat] integer:{}", integer);
        });
    }

    /**
     * void forEach(Consumer<? super T> action)
     * 作用: 对容器中的每个元素并行执行action指定的动作
     */
    @Test
    void forEach() {
        integerList.stream()
                .forEach(integer -> {
                    log.info("[forEach] integer:{}", integer);
                });
    }

    /**
     * void forEachOrdered(Consumer<? super T> action)
     * 作用: 对容器中的每个元素顺序执行action指定的动作
     */
    @Test
    void forEachOrdered() {
        integerList.stream()
                .forEachOrdered(integer -> {
                    log.info("[forEachOrdered] integer:{}", integer);
                });
    }

    /**
     * Object[] toArray()
     * 作用: 转数组
     */
    @Test
    void toArray() {
        Object[] objects = integerList.stream()
                .toArray();
        log.info("[toArray] objects:{}", Arrays.asList(objects));
    }

    /**
     * T reduce(T identity, BinaryOperator<T> accumulator)
     * Optional<T> reduce(BinaryOperator<T> accumulator)
     * <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
     * 作用: 使用所提供的标识、累加和组合功能对该流的元素进行聚合
     */
    @Test
    void reduce() {
        log.info("[reduce] stringList{}", stringList);

        // 字符串长度之和
        // (identity: 初始值, accumulator: 累加器, combiner: 部分和拼接器，并行执行时才会用到)
        Integer sum1 = stringList.stream()
                .reduce(0,
                        (sum, str) -> sum + str.length(),
                        (a, b) -> a - b); //  (a, b) -> a - b, 没有触发。因为是顺序流
        log.info("[reduce] sum1:{}", sum1);
        // 字符串长度之和
        Integer sum2 = stringList.parallelStream()
                .reduce(0,
                        (sum, str) -> sum + str.length(),
                        (a, b) -> a + b);
        log.info("[reduce] sum2:{}", sum2);
        // 字符串长度之和
        int sum3 = stringList.stream()
                .mapToInt(str -> str.length())
                .sum();
        log.info("[reduce] sum3:{}", sum3);

        // 长度最长的字符串
        Optional<String> maxString1 = stringList.stream()
                .reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);
        log.info("[reduce] maxString1:{}", maxString1);
        // min 长度最短的字符串
        Optional<String> maxString3 = stringList.stream()
                .min((s1, s2) -> s1.length() - s2.length());
        log.info("[reduce] maxString3:{}", maxString3);
        // max 长度最长的字符串
        Optional<String> maxString2 = stringList.stream()
                .max((s1, s2) -> s1.length() - s2.length());
        log.info("[reduce] maxString2:{}", maxString2);

        // 字符串长度>3且字符串长度<7的个数
        Integer count = stringList.stream()
                .reduce(
                        0,
                        (sum, str) -> {
                            if (str.length() > 3 && str.length() < 7) {
                                return sum + 1;
                            } else {
                                return sum;
                            }
                        },
                        (a, b) -> a + b
                );
        log.info("[reduce] count:{}", count);
    }

    /**
     * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
     * <R, A> R collect(Collector<? super T, A, R> collector)
     * 作用: 转容器
     */
    @Test
    void collect() {
        // 转List
        ArrayList<Integer> collect1 = integerList.stream()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        log.info("[collect] collect1{}", collect1);
        // 转List
        Collector<Integer, ?, List<Integer>> listCollector2 = Collectors.toList();
        List<Integer> collect2 = integerList.stream()
                .collect(listCollector2);
        log.info("[collect] collect2{}", collect2);
        // 转List
        Collector<Object, ?, List<Object>> objectListCollector = Collectors.toList();

        // 转Set
        Collector<Integer, ?, Set<Integer>> setCollector3 = Collectors.toSet();
        Set<Integer> collect3 = integerList.stream()
                .collect(setCollector3);
        log.info("[collect] collect3{}", collect3);

        // 转Map
        Map<Integer, Integer> collect4 = integerList.stream()
                .collect(Collectors.toMap(
                        integer -> {
                            return integer;
                        },
                        integer -> {
                            return integer + 1;
                        }
                ));
        log.info("[collect] collect4{}", collect4);
        // 转Map
        Collector<String, ?, Map<String, String>> mapCollector5 = Collectors.toMap(string -> "key[" + string + "]", string -> "value[" + string + "]");
        Map<String, String> collect5 = stringList.stream()
                .collect(mapCollector5);
        log.info("[collect] collect5{}", collect5);
    }
}