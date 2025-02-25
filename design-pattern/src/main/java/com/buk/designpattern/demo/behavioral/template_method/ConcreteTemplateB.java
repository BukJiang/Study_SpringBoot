package com.buk.designpattern.demo.behavioral.template_method;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体模板】
 * - 实现抽象类中所定义的抽象方法和钩子方法，是逻辑的一个组成步骤
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class ConcreteTemplateB extends AbstractTemplate {

    /**
     * 抽象方法
     */
    @Override
    protected void abstractMethod() {
        log.info("[具体]抽象方法B");
    }

    /**
     * 具体方法
     */
    @Override
    protected void specificMethod() {
        log.info("[具体]具体方法B");
    }
}
