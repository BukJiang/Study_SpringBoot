package com.buk.designpattern.demo.behavioral.interpreter;

/**
 * 【环境类】
 * - 通常包含各个解释器需要的数据或是相关的操作，一版用来传递被所有解释器共享的数据，后面的解释器可以从这里获取这些值
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class Context {

    /**
     * 【抽象表达式】
     */
    private AbstractExpression expression;

    public Context() {
    }

    /**
     * 操作
     *
     * @param info
     */
    public void operation(String info) {

    }
}
