package com.buk.designpattern.demo.behavioral.iterator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 【具体聚合】
 * - 实现抽象聚合类，返回一个具体迭代器的实例
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
public class ConcreteAggregate implements Aggregate {

    /**
     * 集合
     */
    private List<Object> list = Lists.newArrayList();

    /**
     * 添加
     *
     * @param obj
     */
    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    /**
     * 移除
     *
     * @param obj
     */
    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    /**
     * 迭代器
     *
     * @return
     */
    @Override
    public Iterator getIterator() {
        return new ConcreteIterator(list);
    }
}
