package com.buk.utils.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.Collator;
import java.util.*;

/**
 * TODO: 集合排序
 *
 * @author BuK
 * @date 2020/11/10
 **/
@Slf4j
public class CollectionsSort {

    private static final List<Integer> INTEGER_LIST = Arrays.asList(10, 2, 3, 5, 9, 1, 7, 4, 8, 6);

    private static final List<User> USER_LIST = Arrays.asList(
            new User(1, "一号"), new User(2, "二号"), new User(3, "三号")
    );

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {

        private Integer id;

        private String name;
    }

    /**
     * Collections.sort，对集合进行默认升序排序
     */
    public static void sort1() {
        List<Integer> integers = new ArrayList<>(INTEGER_LIST);
        Collections.sort(integers);
        log.info("[集合排序-sort1]: {}", integers);
    }

    /**
     * Collections.sort，对集合指定comparator排序
     */
    public static void sort2() {
        List<Integer> integers = new ArrayList<>(INTEGER_LIST);
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        log.info("[集合排序-sort2]: " + integers);
    }

    /**
     * Collections.sort，对集合指定comparator排序
     */
    public static void sort3() {
        Comparator<Object> chineseCompare = Collator.getInstance(Locale.CHINESE);

        // 直接比较字符
        List<User> users1 = new ArrayList<>(USER_LIST);
        Collections.sort(users1, chineseCompare);
        log.info("[集合排序-sort1]: {}", users1);

        // 比较对象属性值
        List<User> users2 = new ArrayList<>(USER_LIST);
        Collections.sort(users2, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return chineseCompare.compare(o2.getName(), o1.getName());
            }
        });
        log.info("[集合排序-sort1]: " + users2);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        sort1();
        sort2();
        sort3();
    }
}