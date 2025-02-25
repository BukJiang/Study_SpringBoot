package com.buk.designpattern.demo.behavioral.command;

import lombok.extern.slf4j.Slf4j;

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
        Invoker invoker;

        invoker = new Invoker(new ConcreteCommandA());
        invoker.call();

        invoker = new Invoker(new ConcreteCommandB());
        invoker.call();
    }

    /**
     * 命令类
     */
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    /**
     * 呼叫
     */
    public void call() {
        log.info("请求者: 呼叫...");
        command.execute();
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
