package com.buk.designpattern.demo.behavioral.iterator;

/**
 * 【抽象迭代器】
 * - 定义访问和遍历聚合元素的接口，通常包含 hasNext()、first()、next() 等方法
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public interface Iterator {

    /**
     * 第一个
     *
     * @return
     */
    Object first();

    /**
     * 下一个
     *
     * @return
     */
    Object next();

    /**
     * 是否有下一个
     *
     * @return
     */
    boolean hasNext();
}
