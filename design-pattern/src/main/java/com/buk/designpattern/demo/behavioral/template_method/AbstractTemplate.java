package com.buk.designpattern.demo.behavioral.template_method;

import lombok.extern.slf4j.Slf4j;

/**
 * 【抽象模板类】
 * - 负责给出一个算法的轮廓和骨架。它由一个模板方法和若干个基本方法构成。这些方法的定义如下：
 * -    1. 模板方法：定义了算法的骨架，按某种顺序调用其包含的基本方法
 * -    2. 基本方法：是整个算法中的一个步骤，包含以下几种类型：
 * -        2.1. 抽象方法：在抽象类中声明，由具体子类实现
 * -        2.2. 具体方法：在抽象类中已经实现，在具体子类中可以继承或重写它
 * -        2.3. 钩子方法：在抽象类中已经实现，包括用于判断的逻辑方法和需要子类重写的空方法两种
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public abstract class AbstractTemplate {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        AbstractTemplate concreteTemplateA = new ConcreteTemplateA();
        concreteTemplateA.templateMethod();
        AbstractTemplate concreteTemplateB = new ConcreteTemplateB();
        concreteTemplateB.templateMethod();
        AbstractTemplate concreteTemplateC = new ConcreteTemplateC();
        concreteTemplateC.templateMethod();
        ConcreteTemplateD concreteTemplateD = new ConcreteTemplateD();
        concreteTemplateD.templateMethod();
    }

    /**
     * 模板方法
     */
    public final void templateMethod() {
        log.info("[模板方法]-------");
        abstractMethod();
        specificMethod();
    }

    /**
     * 抽象方法
     */
    protected abstract void abstractMethod();

    /**
     * 具体方法
     */
    protected void specificMethod() {
        log.info("[默认]具体方法");
        hookMethod();
    }

    /**
     * 钩子方法
     */
    protected void hookMethod() {
        log.info("[默认]钩子方法");
    }
}
