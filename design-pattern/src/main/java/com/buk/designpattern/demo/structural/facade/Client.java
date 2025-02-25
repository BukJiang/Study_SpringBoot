package com.buk.designpattern.demo.structural.facade;

/**
 * 【客户端】
 * - 通过一个外观角色访问各个子系统的功能
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public class Client {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Facade facade = new ConcreteFacade();
        facade.execute();
    }
}
