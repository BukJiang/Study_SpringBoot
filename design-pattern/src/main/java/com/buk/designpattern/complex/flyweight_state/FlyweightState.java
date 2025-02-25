package com.buk.designpattern.complex.flyweight_state;

/**
 * 【抽象享元状态】
 * - 定义一个接口，用以封装环境对象中的特定状态所对应的行为，可以有一个或多个行为
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public abstract class FlyweightState {

    /**
     * 处理
     *
     * @param flyweightContext
     */
    public abstract void handle(FlyweightContext flyweightContext);
}
