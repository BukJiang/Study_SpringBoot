package com.buk.designpattern.demo.behavioral.visitor;

/**
 * 【抽象访问者】
 * - 定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
public interface Visitor {

    /**
     * 访问
     *
     * @param element
     */
    void visit(ConcreteElementA element);

    /**
     * 访问
     *
     * @param element
     */
    void visit(ConcreteElementB element);
}
