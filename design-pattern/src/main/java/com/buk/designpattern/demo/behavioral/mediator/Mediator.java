package com.buk.designpattern.demo.behavioral.mediator;

/**
 * 【抽象中介者】
 * - 是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象接口
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public abstract class Mediator {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Colleague concreteColleagueA = new ConcreteColleagueA("A");
        Colleague concreteColleagueB = new ConcreteColleagueB("B");
        mediator.register(concreteColleagueA);
        mediator.register(concreteColleagueB);
        concreteColleagueA.send("A发的");
        concreteColleagueB.send("B发的");
    }

    /**
     * 注册
     *
     * @param colleague
     */
    public abstract void register(Colleague colleague);

    /**
     * 转发
     *
     * @param colleague
     * @param info
     */
    public abstract void relay(Colleague colleague, String info);
}
