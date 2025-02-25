package com.buk.designpattern.demo.behavioral.state;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体状态】
 * - 实现抽象状态所对应的行为，并且在需要的情况下进行状态切换
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class ConcreteStateB extends State {

    /**
     * 处理
     *
     * @param context
     */
    @Override
    public void handle(Context context) {
        log.info("[具体状态]B");
        context.setState(new ConcreteStateA());
    }
}
