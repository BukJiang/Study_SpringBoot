package com.buk.designpattern.demo.behavioral.interpreter;

import lombok.extern.slf4j.Slf4j;

/**
 * 【非终结符表达式】
 * - 是抽象表达式的子类，用来实现文法中与非终结符相关的操作，文法中的每一个终结符都有一个具体非终结表达式与之相对应
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class NonTerminalExpression implements AbstractExpression {

    /**
     * 解释
     *
     * @param info
     */
    @Override
    public void interpret(String info) {
        log.info("[非终结符表达式]:{}", info);
    }
}
