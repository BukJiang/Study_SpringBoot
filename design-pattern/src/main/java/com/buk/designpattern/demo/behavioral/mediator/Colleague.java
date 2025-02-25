package com.buk.designpattern.demo.behavioral.mediator;

/**
 * 【抽象同事】
 * - 定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public abstract class Colleague {

    protected String name;

    /**
     * 【抽象中介者】
     */
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * 接收
     *
     * @param info
     */
    public abstract void receive(String info);

    /**
     * 发送
     *
     * @param info
     */
    public abstract void send(String info);
}
