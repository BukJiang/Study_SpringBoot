package com.buk.utils.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * TODO: 异步任务
 *
 * @author jiangbk
 * @date 2020/11/30
 **/
@Slf4j
public class MyFutureTask {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 异步执行服务
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 异步任务
        List<FutureTask<Integer>> tasks = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            FutureTask<Integer> futureTask = new FutureTask<>(() -> {
                Thread.sleep(finalI * 1000);
                return finalI;
            });
            tasks.add(futureTask);
        }
        tasks.forEach(executorService::submit);

        // 异步执行结果
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Integer integer = null;
            FutureTask<Integer> futureTask = tasks.get(i);
            log.info("[异步任务], 次数:{}", num);
            try {
                integer = futureTask.get();
                log.info("[异步任务], integer:{}", integer);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                log.info("[异步任务], finally:{}", num);
                futureTask.cancel(true);
            }
            if (integer != null) {
                tasks.stream().skip(num).forEach(task -> task.cancel(true));
                break;
            }
        }

        long end = System.currentTimeMillis();
        log.info("[异步任务], 耗时:{}", (end - start));
    }
}
