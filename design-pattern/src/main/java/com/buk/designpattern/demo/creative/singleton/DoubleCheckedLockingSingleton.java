package com.buk.designpattern.demo.creative.singleton;

import java.util.DoubleSummaryStatistics;
import java.util.Objects;

/**
 * 【单例类】- 双检锁(DCL，即 double-checked locking)
 * - 包含一个实例且能自行创建这个实例的类
 *
 * @author jiangbk
 * @date 2021/3/16
 **/
public class DoubleCheckedLockingSingleton {

    /**
     * volatile: 被volatile修饰的变量，保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象。
     * eg: private volatile static DoubleCheckedLockingSingleton instance;
     */
    private static DoubleCheckedLockingSingleton instance;

    /**
     * 双检锁(DCL，即 double-checked locking)
     * - 采用双锁机制，安全且在多线程情况下能保持高性能
     *
     * @return
     */
    public static DoubleCheckedLockingSingleton getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (DoubleSummaryStatistics.class) {
                if (Objects.isNull(instance)) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    private DoubleCheckedLockingSingleton() {
    }
}
