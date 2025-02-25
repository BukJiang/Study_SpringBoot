package com.buk.utils.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * TODO: 异步任务-FutureTask
 */
@Slf4j
@SpringBootTest
class FutureTaskTest {

    /**
     * @throws Exception
     */
    @Test
    void futureTaskBreak() throws Exception {
        long start = System.currentTimeMillis();

        // 异步执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 异步任务
        List<FutureTask<Integer>> futureTaskList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            FutureTask<Integer> futureTask = new FutureTask<>(() -> {
                Thread.sleep(finalI * 1000);
                return finalI;
            });
            futureTaskList.add(futureTask);
        }
        futureTaskList.forEach(executorService::submit);
        //
        for (int i = 0; i < futureTaskList.size(); i++) {
            Integer integer = futureTaskList.get(i).get();
            log.info("[异步任务-FutureTask]: integer-{}", integer);
            if (integer != null) {
                int skipI = i + 1;
                futureTaskList.stream()
                        .skip(skipI)
                        .forEach(task -> {
                            task.cancel(true);
                        });
                break;
            }
        }

        long end = System.currentTimeMillis();
        log.info("[异步任务-FutureTask]: 耗时-{}ms", (end - start));
    }
}
