package com.buk.designpattern.demo.structural.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体装饰】
 * - 实现抽象装饰的相关方法，并给具体构件对象添加附加的责任
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
@Slf4j
public class ConcreteDecoratorB extends Decorator {

    protected ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        log.info("具体装饰B - 操作");
    }
}
