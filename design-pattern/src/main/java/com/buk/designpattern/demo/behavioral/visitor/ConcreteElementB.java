package com.buk.designpattern.demo.behavioral.visitor;

/**
 * 【具体元素】
 * - 实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，另外具体元素中可能还包含本身业务逻辑的相关操作
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
public class ConcreteElementB implements Element {

    /**
     * 接收
     *
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * 操作
     *
     * @return
     */
    public String operation() {
        return "[具体元素]B";
    }
}
