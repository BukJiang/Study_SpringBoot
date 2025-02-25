package com.buk.designpattern.complex.strategy_factory;

/**
 * 策略类型枚举
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public enum StrategyTypeEnum {

    //
    STRATEGY_A("策略A"),
    STRATEGY_B("策略B");

    public final String description;

    StrategyTypeEnum(String description) {
        this.description = description;
    }
}
