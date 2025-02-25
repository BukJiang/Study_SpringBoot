package com.buk.designpattern.complex.composite_command;

import com.buk.designpattern.demo.behavioral.command.Command;
import com.buk.designpattern.demo.behavioral.command.ReceiverA;

/**
 * 【具体命令类】
 * - 是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作
 *
 * @author jiangbk
 * @date 2021/3/17
 **/
public class ConcreteCommandA implements Command {

    /**
     * 接收者
     */
    private ReceiverA receiverA;

    public ConcreteCommandA() {
        receiverA = new ReceiverA();
    }

    /**
     * 执行
     */
    @Override
    public void execute() {
        receiverA.action("命令A");
    }
}
