package com.buk.utils.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * TODO: 数组排序
 */
@Slf4j
@SpringBootTest
class ArraysSortTest {

    private static final Integer[] INTEGER_ARR = new Integer[]{10, 2, 3, 5, 9, 1, 7, 4, 8, 6};

    /**
     * Arrays.sort，对数组进行默认升序排序
     */
    @Test
    void sort1() {
        Integer[] integers = Arrays.copyOf(INTEGER_ARR, 10);
        Arrays.sort(integers);
        log.info("[数组排序-sort1]: {}", Arrays.toString(integers));
    }

    /**
     * Arrays.sort，对数组指定comparator排序
     */
    @Test
    void sort2() {
        Integer[] integers = Arrays.copyOf(INTEGER_ARR, 10);
        Arrays.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        log.info("[数组排序-sort2]: {}", Arrays.toString(integers));
    }
}
