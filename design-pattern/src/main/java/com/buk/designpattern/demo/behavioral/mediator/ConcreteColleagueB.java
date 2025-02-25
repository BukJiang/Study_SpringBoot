package com.buk.designpattern.demo.behavioral.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体同事】
 * - 是抽象同事类的实现着，当需要与其他同事对象交互时，由中介者对象负责后续的交互
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class ConcreteColleagueB extends Colleague {

    public ConcreteColleagueB(String name) {
        this.name = name;
    }

    /**
     * 接收
     *
     * @param info
     */
    @Override
    public void receive(String info) {
        log.info("[具体同事B]收到:{}", info);
    }

    /**
     * 发送
     *
     * @param info
     */
    @Override
    public void send(String info) {
        log.info("-----------");
        log.info("[具体同事B]发送:{}", info);
        mediator.relay(this, info);
    }
}
