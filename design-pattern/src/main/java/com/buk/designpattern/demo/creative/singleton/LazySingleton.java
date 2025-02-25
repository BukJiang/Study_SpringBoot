package com.buk.designpattern.demo.creative.singleton;

import java.util.Objects;

/**
 * 【单例类】- 懒汉式
 * - 包含一个实例且能自行创建这个实例的类
 *
 * @author jiangbk
 * @date 2021/3/16
 **/
public class LazySingleton {

    private static LazySingleton instance;

    /**
     * 懒汉式，线程不安全
     *
     * @return
     */
    public static LazySingleton lazyUnsafe() {
        if (Objects.isNull(instance)) {
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 懒汉式，线程安全
     * - 效率很低
     *
     * @return
     */
    public static synchronized LazySingleton lazySafe() {
        if (Objects.isNull(instance)) {
            instance = new LazySingleton();
        }
        return instance;
    }

    private LazySingleton() {
    }
}
