package com.buk.designpattern.demo.behavioral.interpreter;

/**
 * 【抽象表达式】
 * - 定义解释器的接口，约定解释器的解释操作，主要包含解释方法 interpret()
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public interface AbstractExpression {

    /**
     * 解释
     *
     * @param info
     */
    void interpret(String info);
}
