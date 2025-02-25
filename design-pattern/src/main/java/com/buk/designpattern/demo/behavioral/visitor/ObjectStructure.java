package com.buk.designpattern.demo.behavioral.visitor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 【对象结构】
 * - 是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，通常由 List、Set、Map 等聚合类实现
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
public class ObjectStructure {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new ConcreteElementA());
        objectStructure.add(new ConcreteElementB());
        Visitor visitorA = new ConcreteVisitorA();
        objectStructure.accept(visitorA);
        Visitor visitorB = new ConcreteVisitorB();
        objectStructure.accept(visitorB);
    }

    /**
     * 元素集合
     */
    private List<Element> list = Lists.newArrayList();

    /**
     * 接收
     *
     * @param visitor
     */
    public void accept(Visitor visitor) {
        for (Element element : list) {
            element.accept(visitor);
        }
    }

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(Element element) {
        list.add(element);
    }

    /**
     * 移除元素
     *
     * @param element
     */
    public void remove(Element element) {
        list.remove(element);
    }
}
