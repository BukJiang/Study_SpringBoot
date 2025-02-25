package com.buk.designpattern.demo.behavioral.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * 【终结符表达式】
 * - 是抽象表达式的子类，用来实现文法中与终结符相关的操作，文法中的每一个终结符都有一个具体终结表达式与之相对应
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class TerminalExpression implements AbstractExpression {

    /**
     * 解释
     *
     * @param info
     */
    @Override
    public void interpret(String info) {
        log.info("[终结符表达式]:{}", info);
    }
}
