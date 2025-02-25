package com.buk.designpattern.demo.creative.singleton;

/**
 * 【单例类】- 枚举式
 * - 包含一个实例且能自行创建这个实例的类
 *
 * @author jiangbk
 * @date 2021/3/16
 **/
public class EnumSingleton {

    /**
     * 静态枚举类
     */
    enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;

        private EnumSingleton instance;

        SingletonEnum() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }

    /**
     * 枚举式
     * - 实现方式还没有被广泛采用，但这是实现单例模式的最佳方法
     * - 更简洁，自动支持序列化机制，绝对防止多次实例化
     * - 是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
     *
     * @return
     */
    public static EnumSingleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    private EnumSingleton() {
    }
}
