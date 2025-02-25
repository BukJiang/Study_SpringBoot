package com.buk.designpattern.demo.behavioral.chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;

/**
 * 【客户端】
 * - 组装责任链，并向链头的具体处理者对象提交请求，它不关心处理细节和请求的传递过程
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class Client {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Handler concreteHandlerA = new ConcreteHandlerA();
        Handler concreteHandlerB = new ConcreteHandlerB();
        Handler concreteHandlerC = new ConcreteHandlerC();
        // 组装责任链
        concreteHandlerA.setNext(concreteHandlerB);
        concreteHandlerB.setNext(concreteHandlerC);
        // 请求
        String result = concreteHandlerA.handle("请求数据");
        log.info("[客户端]请求结果 - {}", result);
    }
}
