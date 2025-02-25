package com.buk.designpattern.demo.structural.decorator;

/**
 * 【抽象装饰】
 * - 继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
abstract class Decorator implements Component {

    /**
     * 具体构建
     */
    protected Component component;

    protected Decorator(Component component) {
        this.component = component;
    }

    /**
     * 操作
     */
    @Override
    public void operation() {
        component.operation();
    }
}
