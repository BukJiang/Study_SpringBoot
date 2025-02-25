package com.buk.designpattern.complex.strategy_factory;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * 【环境类】
 * - 持有策略类的引用，最终给客户端调用
 * - 在环境类中使用工厂模式来管理策略类将大大减少客户端的工作复杂度
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public class Context {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Context context = new Context();
        context.putStrategy(StrategyTypeEnum.STRATEGY_A, new ConcreteStrategyA());
        context.putStrategy(StrategyTypeEnum.STRATEGY_B, new ConcreteStrategyB());
        context.strategyMethod(StrategyTypeEnum.STRATEGY_A);
        context.strategyMethod(StrategyTypeEnum.STRATEGY_B);
    }

    /**
     * 【抽象策略】
     */
    private HashMap<StrategyTypeEnum, Strategy> strategyMap;

    public Context() {
        strategyMap = Maps.newHashMap();
    }

    /**
     * 【抽象方法】
     *
     * @param strategyTypeEnum
     */
    public void strategyMethod(StrategyTypeEnum strategyTypeEnum) {
        Objects.requireNonNull(strategyTypeEnum);
        Optional.ofNullable(getStrategy(strategyTypeEnum))
                .ifPresent(Strategy::strategyMethod);
    }

    public Strategy getStrategy(StrategyTypeEnum strategyTypeEnum) {
        return strategyMap.get(strategyTypeEnum);
    }

    public void putStrategy(StrategyTypeEnum strategyTypeEnum, Strategy strategy) {
        strategyMap.put(strategyTypeEnum, strategy);
    }
}
