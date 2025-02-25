package com.buk.designpattern.demo.creative.singleton;

/**
 * 【单例类】- 登记式/静态内部类
 * - 包含一个实例且能自行创建这个实例的类
 *
 * @author jiangbk
 * @date 2021/3/16
 **/
public class RegisterSingleton {

    private static class RegisterSingletonHolder {
        private static final RegisterSingleton INSTANCE = new RegisterSingleton();
    }

    /**
     * 登记式/静态内部类
     * - 能够达到双检锁方式一样的功效，但实现更简单
     * - 登记式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用
     *
     * @return
     */
    public static RegisterSingleton getInstance() {
        return RegisterSingletonHolder.INSTANCE;
    }

    private RegisterSingleton() {
    }
}
