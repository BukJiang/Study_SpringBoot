package com.buk.scheduled.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO: 定时任务
 * <p>
 * TODO: Cron表达式由6或7个空格分隔的时间字段组成：
 * 秒 分钟 小时 日期 月份 星期 年(可选)
 * <p>
 * 字段　　    允许值　　   允许的特殊字符
 * 秒     　   0-59 　　　　, - * /
 * 分     　   0-59　　　　 , - * /
 * 时         0-23 　　　　, - * /
 * 日         1-31 　　　　, - * ? / L W C
 * 月         1-12 　　　　, - * /
 * 星期        1-7 　　　　 , - * ? / L C #
 * 年(可选)   1970-2099 　　, - * /
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@Component
public class MyScheduled {

    /**
     * 固定频率 /3s
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("[定时任务-fixedRate]: {}ms", System.currentTimeMillis());
    }

    /**
     * 固定延迟 /3s
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelay() throws InterruptedException {
        // 相当于10s一次
        Thread.sleep(5000);
        log.info("[定时任务-fixedDelay]: {}ms", System.currentTimeMillis());
    }

    /**
     * cron 表达式
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void cron() {
        log.info("[定时任务-cron]: {}ms", System.currentTimeMillis());
    }
}
