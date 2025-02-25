package com.buk.designpattern.demo.structural.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体构建】
 * - 实现抽象构件，通过装饰角色为其添加一些职责
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
@Slf4j
public class ConcreteComponent implements Component {

    @Override
    public void operation() {
        log.info("具体构建 - 操作");
    }

    public ConcreteComponent() {
        log.info("具体构建 - 创建");
    }
}
