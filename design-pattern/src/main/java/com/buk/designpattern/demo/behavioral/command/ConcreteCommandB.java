package com.buk.designpattern.demo.behavioral.command;

/**
 * 【具体命令类】
 * - 是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作
 *
 * @author jiangbk
 * @date 2021/3/17
 **/
public class ConcreteCommandB implements Command {

    /**
     * 接收者
     */
    private ReceiverB receiverB;

    public ConcreteCommandB() {
        receiverB = new ReceiverB();
    }

    /**
     * 执行
     */
    @Override
    public void execute() {
        receiverB.action("命令B");
    }
}
