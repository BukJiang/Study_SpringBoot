package com.buk.interceptor.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: 拦截控制
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@RestController
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/allow")
    public void allow() {
        log.info("[Controller]-[拦截控制器] -> 允许访问，InterceptorController.allow");
    }

    @RequestMapping("/redirect")
    public void redirect() {
        log.info("[Controller]-[拦截控制器] -> 拦截访问，InterceptorController.redirect");
    }
}
