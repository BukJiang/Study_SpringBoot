package com.buk.designpattern.demo.creative.singleton;

/**
 * 【单例类】- 饿汉式
 * - 包含一个实例且能自行创建这个实例的类
 *
 * @author jiangbk
 * @date 2021/3/16
 **/
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    /**
     * 饿汉式
     * - 具备很好的 lazy loading，能够在多线程中很好的工作
     * - 效率很低，99% 情况下不需要同步
     *
     * @return
     */
    public static HungrySingleton getInstance() {
        return instance;
    }

    private HungrySingleton() {
    }
}
