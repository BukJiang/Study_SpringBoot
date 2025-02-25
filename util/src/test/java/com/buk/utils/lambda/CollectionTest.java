package com.buk.utils.lambda;

import com.buk.utils.lambda.pojo.dto.User;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * TODO: Lambda - Collection
 * <p>
 * Collection: forEach、removeIf
 * List: replaceAll、sort
 */
@Slf4j
@SpringBootTest
class CollectionTest {

    private static List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private static List<String> stringList = Lists.newArrayList("", "1", "22", "333", "4444", "55555", "666666", "7777777", "88888888", "999999999");

    private static List<User> userList = Lists.newArrayList(new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User(), new User());

    /**
     * void forEach(Consumer<? super E> action)
     * 作用: 对容器中的每个元素执行action动作
     */
    @Test
    void forEach() {
        integerList.forEach(System.out::println);
        stringList.forEach(System.out::println);
    }

    /**
     * Spliterator<E> spliterator()
     * 作用: 可拆分迭代器
     */
    @Test
    void spliterator() {
        // 拆分任务
        Spliterator<User> spliterator1 = userList.spliterator();
        Spliterator<User> spliterator2 = spliterator1.trySplit();
        log.info("[spliterator] getExactSizeIfKnown1:{}", spliterator1.getExactSizeIfKnown());
        log.info("[spliterator] getExactSizeIfKnown2:{}", spliterator2.getExactSizeIfKnown());
        // spliterator1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                spliterator1.forEachRemaining(user -> {
                    user.setName("-" + user.getName() + "-");
                });
            }
        });
        thread1.start();
        // spliterator2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                spliterator2.forEachRemaining(user -> {
                    user.setName("=" + user.getName() + "=");
                });
            }
        });
        thread2.start();
        // userList
        log.info("[spliterator] userList{}", userList);
    }

    /**
     * boolean removeIf(Predicate<? super E> filter)
     * 作用: 移除容器中满足filter条件的元素
     */
    @Test
    void removeIf() {
        log.info("[removeIf] integerList{}", integerList);
        boolean a = integerList.removeIf(integer -> integer < 3 || integer > 7);
        log.info("[removeIf] integerList{}", integerList);

        log.info("[removeIf] stringList{}", stringList);
        boolean b = stringList.removeIf(string -> string.length() < 3 || string.length() > 7);
        log.info("[removeIf] stringList{}", stringList);
    }

    /**
     * Stream<E> stream()
     * 作用: 返回该容器的顺序Stream视图表示
     */
    @Test
    void stream() {
        Stream<Integer> integerStream = integerList.stream();
        Stream<String> stringStream = stringList.stream();
        Stream<User> userStream = userList.stream();
    }

    /**
     * Stream<E> parallelStream()
     * 作用: 返回该容器的并行Stream视图表示
     */
    @Test
    void parallelStream() {
        Stream<Integer> integerStream = integerList.parallelStream();
        Stream<String> stringStream = stringList.parallelStream();
        Stream<User> userStream = userList.parallelStream();
    }

    /**
     * void replaceAll(UnaryOperator<E> operator)
     * 作用: 对容器中的每个元素执行operator操作，并将元素赋值为操作结果
     */
    @Test
    void replaceAll() {
        log.info("[removeIf] integerList{}", integerList);
        integerList.replaceAll(integer -> integer++);
        log.info("[removeIf] integerList{}", integerList);

        log.info("[removeIf] stringList{}", stringList);
        stringList.replaceAll(string -> "-" + string + "-");
        log.info("[removeIf] stringList{}", stringList);
    }

    /**
     * void sort(Comparator<? super E> c)
     * 作用: 根据c指定的比较规则对容器中的元素进行排序
     */
    @Test
    void sort() {
        log.info("[removeIf] integerList{}", integerList);
        integerList.sort((integer1, integer2) -> integer2 - integer1);
        log.info("[removeIf] integerList{}", integerList);

        log.info("[removeIf] stringList{}", stringList);
        stringList.sort((string1, string2) -> string2.length() - string1.length());
        log.info("[removeIf] stringList{}", stringList);
    }
}
