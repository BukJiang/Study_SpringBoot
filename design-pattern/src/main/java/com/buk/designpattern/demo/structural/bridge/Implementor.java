package com.buk.designpattern.demo.structural.bridge;

/**
 * 【实现化】
 * - 定义实现化角色的接口，供扩展抽象化角色调用
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public interface Implementor {

    /**
     * 操作实现
     */
    void operationImpl();
}
