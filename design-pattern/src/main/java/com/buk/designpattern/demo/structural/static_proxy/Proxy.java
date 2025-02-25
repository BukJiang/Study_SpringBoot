package com.buk.designpattern.demo.structural.static_proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 【代理】
 * - 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class Proxy implements Subject {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }

    /**
     * 【真实主题】
     */
    private RealSubject realSubject;

    /**
     * 请求
     */
    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }

    /**
     * 请求前
     */
    public void preRequest() {
        log.info("[代理]请求前");
    }

    /**
     * 请求后
     */
    public void postRequest() {
        log.info("[代理]请求后");
    }
}
