package com.buk.log.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 日志
 *
 * @author BuK
 * @since 2020/08/20
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {

    @RequestMapping("/info")
    public void info() {
        log.info("[Controller]-[日志]-info: {}", RandomUtils.nextLong());
    }

    @RequestMapping("/error")
    public void error() {
        log.error("[Controller]-[日志]-error: {}", RandomUtils.nextLong());
    }
}
