package com.buk.designpattern.demo.structural.static_proxy;

/**
 * 【抽象主题】
 * - 通过接口或抽象类声明真实主题和代理对象实现的业务方法
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public interface Subject {

    /**
     * 请求
     */
    void request();
}
