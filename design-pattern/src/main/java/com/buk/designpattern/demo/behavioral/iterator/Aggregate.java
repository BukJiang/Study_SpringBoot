package com.buk.designpattern.demo.behavioral.iterator;

/**
 * 【抽象聚合】
 * - 定义存储、添加、删除聚合对象以及创建迭代器对象的接口
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public interface Aggregate {

    /**
     * 添加
     *
     * @param obj
     */
    void add(Object obj);

    /**
     * 移除
     *
     * @param obj
     */
    void remove(Object obj);

    /**
     * 迭代器
     *
     * @return
     */
    Iterator getIterator();
}
