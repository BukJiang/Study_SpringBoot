package com.buk.advicecontroller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 控制器增强
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@RestController
@RequestMapping("/advice")
public class AdviceController {

    @RequestMapping("/exceptionHandler")
    public void exceptionHandler() {
        log.info("[控制器增强]-统一异常处理: exceptionHandler");
        throw new RuntimeException("/advice/exceptionHandler 出错了");
    }

    @RequestMapping("/initBinder")
    public void initBinder(String param) {
        log.info("[控制器增强]-初始化绑定器: param->{}", param);
    }
}
