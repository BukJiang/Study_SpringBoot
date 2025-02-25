package com.buk.designpattern.demo.structural.static_proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 【真实主题】
 * - 实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class RealSubject implements Subject {

    /**
     * 请求
     */
    @Override
    public void request() {
        log.info("[真实主题]");
    }
}
