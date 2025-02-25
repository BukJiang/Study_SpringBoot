package com.buk.advicecontroller.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO: 响应工具测试
 */
@Slf4j
@SpringBootTest
class ResponseUtilTest {

    @Test
    void init() {
        ResponseUtil init = ResponseUtil.init();
        log.info("[响应工具测试]-init: {}", init);

        ResponseUtil ok = ResponseUtil.ok();
        log.info("[响应工具测试]-ok: {}", ok);

        ok.message("链式message - ok").data("链式data - data").pageInfo(1L, 10L, 111L);
        log.info("[响应工具测试]-链式ok: {}", ok);
    }
}