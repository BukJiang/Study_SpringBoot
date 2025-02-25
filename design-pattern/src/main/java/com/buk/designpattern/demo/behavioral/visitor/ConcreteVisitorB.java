package com.buk.designpattern.demo.behavioral.visitor;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体访问者】
 * - 实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么
 *
 * @author jiangbk
 * @date 2021/4/22
 **/
@Slf4j
public class ConcreteVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA element) {
        log.info("[具体访问者]B访问 -> {}", element.operation());
    }

    @Override
    public void visit(ConcreteElementB element) {
        log.info("[具体访问者]B访问 -> {}", element.operation());
    }
}
