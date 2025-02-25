package com.buk.designpattern.demo.structural.bridge;

/**
 * 【抽象化】
 * - 定义抽象化类，并包含一个对实现化对象的引用
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public abstract class Abstraction {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementor();
        Abstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();
    }

    /**
     * 【实现化】
     */
    protected Implementor implementor;

    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    /**
     * 操作
     */
    public abstract void operation();
}
