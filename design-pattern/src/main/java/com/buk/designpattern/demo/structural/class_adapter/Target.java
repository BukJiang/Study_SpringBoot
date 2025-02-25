package com.buk.designpattern.demo.structural.class_adapter;

/**
 * 【抽象目标】
 * - 客户端需通过抽象目标访问所需的服务
 *
 * @author jiangbk
 * @date 2021/4/16
 **/
public interface Target {

    /**
     * 请求接口
     *
     * @param param
     */
    void request(Object param);
}
