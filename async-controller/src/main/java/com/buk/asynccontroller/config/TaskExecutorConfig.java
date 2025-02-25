package com.buk.asynccontroller.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO: @Async线程池配置
 *
 * @author BuK
 * @date 2020/09/03
 */
@Slf4j
@Configuration
public class TaskExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(600);
        // 设置最大线程数
        executor.setMaxPoolSize(6000);
        // 设置队列容量
        executor.setQueueCapacity(0);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(0);
        // 设置默认线程名称
        executor.setThreadNamePrefix("taskExecutor");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //
        log.info("[@Async线程池配置] - 初始化成功");
        return executor;
    }
}
