package com.buk.designpattern.complex.composite_command;

import com.buk.designpattern.demo.behavioral.command.Command;
import com.buk.designpattern.demo.behavioral.command.ConcreteCommandA;
import com.buk.designpattern.demo.behavioral.command.ConcreteCommandB;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 【请求者】
 * - 是请求的发送者，它通常拥有很多的命令对象
 * - 通过访问命令对象来执行相关请求，它不直接访问接收者
 *
 * @author jiangbk
 * @date 2021/3/17
 **/
@Slf4j
public class Invoker {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        ConcreteCommandA concreteCommandA = new ConcreteCommandA();
        invoker.addCommand(concreteCommandA);
        invoker.call();

        ConcreteCommandB concreteCommandB = new ConcreteCommandB();
        invoker.addCommand(concreteCommandB);
        invoker.call();
    }

    /**
     * 命令类
     */
    private List<Command> commandList;

    public Invoker() {
        this.commandList = Lists.newArrayList();
    }

    /**
     * 呼叫
     */
    public void call() {
        log.info("请求者: 呼叫...");
        commandList.forEach(Command::execute);
    }

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void removeCommand(Command command) {
        commandList.remove(command);
    }
}
