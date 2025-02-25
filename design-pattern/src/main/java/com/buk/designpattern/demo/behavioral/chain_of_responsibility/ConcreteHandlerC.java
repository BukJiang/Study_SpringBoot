package com.buk.designpattern.demo.behavioral.chain_of_responsibility;

import java.util.Objects;

/**
 * 【具体处理者】
 * - 实现抽象处理者的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class ConcreteHandlerC extends Handler {

    /**
     * 处理
     *
     * @param request
     * @return
     */
    @Override
    public String handle(String request) {
        request = "[具体处理者C]:{" + request + "}";
        Handler nextHandler = getNext();
        if (Objects.isNull(nextHandler)) {
            return request;
        }
        return nextHandler.handle(request);
    }
}
