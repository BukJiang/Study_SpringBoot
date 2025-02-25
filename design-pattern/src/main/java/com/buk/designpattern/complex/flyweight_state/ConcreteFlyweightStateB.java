package com.buk.designpattern.complex.flyweight_state;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体享元状态】
 * - 实现抽象状态所对应的行为，并且在需要的情况下进行状态切换
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class ConcreteFlyweightStateB extends FlyweightState {

    /**
     * 处理
     *
     * @param flyweightContext
     */
    @Override
    public void handle(FlyweightContext flyweightContext) {
        log.info("[具体享元状态]B");
        flyweightContext.setFlyweightState(flyweightContext.getFlyweightState("ConcreteFlyweightStateA"));
    }
}
