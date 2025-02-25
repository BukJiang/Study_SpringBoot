package com.buk.designpattern.demo.behavioral.strategy;

/**
 * 【环境类】
 * - 持有策略类的引用，最终给客户端调用
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
        context.setStrategy(new ConcreteStrategyA());
        context.strategyMethod();
        context.setStrategy(new ConcreteStrategyB());
        context.strategyMethod();
    }

    /**
     * 【抽象策略】
     */
    private Strategy strategy;

    /**
     * 【抽象方法】
     */
    public void strategyMethod() {
        strategy.strategyMethod();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
