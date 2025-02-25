package com.buk.designpattern.complex.strategy_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体策略】
 * - 实现了抽象策略定义的接口，提供具体的算法实现
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
@Slf4j
public class ConcreteStrategyA implements Strategy {

    @Override
    public void strategyMethod() {
        log.info("具体策略A");
    }
}
