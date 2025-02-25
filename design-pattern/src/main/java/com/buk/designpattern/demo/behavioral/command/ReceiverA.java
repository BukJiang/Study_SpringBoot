package com.buk.designpattern.demo.behavioral.command;

import lombok.extern.slf4j.Slf4j;

/**
 * 【接收者】
 * - 执行命令功能的相关操作，是具体命令对象业务的真正实现者
 *
 * @author jiangbk
 * @date 2021/3/17
 **/
@Slf4j
public class ReceiverA {

    /**
     * 行为
     *
     * @param command
     */
    public void action(String command) {
        log.info("接受者A: 收到命令-{}", command);
    }
}
