package com.buk.designpattern.demo.structural.decorator;

/**
 * 【抽象构建】
 * - 定义抽象类或者接口以规范准备接收附加责任的对象
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
public interface Component {

    /**
     * 使用示例
     *
     * @param args
     */
    static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecoratorA(component);
        component = new ConcreteDecoratorB(component);
        component.operation();
    }

    /**
     * 操作
     */
    void operation();
}
