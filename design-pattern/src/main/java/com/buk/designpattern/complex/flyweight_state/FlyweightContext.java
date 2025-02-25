package com.buk.designpattern.complex.flyweight_state;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 【享元环境类】
 * - 也称为上下文，定义了客户端需要的接口，内部维护一个当前状态，并负责具体状态的切换
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public class FlyweightContext {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        FlyweightContext flyweightContext = new FlyweightContext();
        flyweightContext.handle();
        flyweightContext.handle();
        flyweightContext.handle();
    }

    /**
     * 【抽象享元状态】
     */
    private FlyweightState flyweightState;

    /**
     * 抽象享元状态Map
     */
    private Map<String, FlyweightState> flyweightStateMap = Maps.newHashMap();

    public FlyweightContext() {
        ConcreteFlyweightStateA concreteFlyweightStateA = new ConcreteFlyweightStateA();
        ConcreteFlyweightStateB concreteFlyweightStateB = new ConcreteFlyweightStateB();
        this.flyweightState = concreteFlyweightStateA;
        flyweightStateMap.put("ConcreteFlyweightStateA", concreteFlyweightStateA);
        flyweightStateMap.put("ConcreteFlyweightStateB", concreteFlyweightStateB);
    }

    /**
     * 获取状态
     *
     * @param key
     * @return
     */
    public FlyweightState getFlyweightState(String key) {
        return flyweightStateMap.get(key);
    }

    /**
     * 设置状态
     *
     * @param flyweightState
     */
    public void setFlyweightState(FlyweightState flyweightState) {
        this.flyweightState = flyweightState;
    }

    /**
     * 处理
     */
    public void handle() {
        flyweightState.handle(this);
    }
}
