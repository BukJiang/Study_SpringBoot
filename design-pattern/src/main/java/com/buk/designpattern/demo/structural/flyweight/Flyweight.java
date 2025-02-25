package com.buk.designpattern.demo.structural.flyweight;

/**
 * 【抽象享元】
 * - 是所有的具体享元类的基类，为具体享元规范需要实现的公共接口，非享元的外部状态以参数的形式通过方法传入
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public interface Flyweight {

    /**
     * 操作
     *
     * @param unsharedConcreteFlyweight
     */
    void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight);
}
