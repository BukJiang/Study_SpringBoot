package com.buk.designpattern.demo.behavioral.visitor;

/**
 * 【抽象元素】
 * - 声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
public interface Element {

    /**
     * 接收
     *
     * @param visitor
     */
    void accept(Visitor visitor);
}
