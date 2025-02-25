package com.buk.designpattern.demo.behavioral.iterator;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 【具体迭代器】
 * - 实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class ConcreteIterator implements Iterator {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("1");
        aggregate.add("2");
        aggregate.add("3");
        Iterator iterator = aggregate.getIterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            log.info("[集合]-元素: {}", next);
        }
    }

    /**
     * 集合
     */
    private List<Object> list;

    /**
     * 序号
     */
    private int index = -1;

    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    /**
     * 第一个
     *
     * @return
     */
    @Override
    public Object first() {
        index = 0;
        return list.get(index);
    }

    /**
     * 下一个
     *
     * @return
     */
    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }

    /**
     * 是否有下一个
     *
     * @return
     */
    @Override
    public boolean hasNext() {
        return index < list.size() - 1;
    }
}
