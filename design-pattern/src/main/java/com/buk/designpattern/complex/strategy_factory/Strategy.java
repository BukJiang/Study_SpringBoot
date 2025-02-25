package com.buk.designpattern.complex.strategy_factory;

/**
 * 【抽象策略】
 * - 定义公共接口，各种不同的算法以不同的方式实现这个接口
 * - 环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public interface Strategy {

    /**
     * 【抽象方法】
     */
    void strategyMethod();
}
