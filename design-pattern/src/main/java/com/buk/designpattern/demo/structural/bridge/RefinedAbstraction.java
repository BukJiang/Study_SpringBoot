package com.buk.designpattern.demo.structural.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 【扩展抽象化】
 * - 是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    /**
     * 操作
     */
    @Override
    public void operation() {
        log.info("[扩展抽象化]操作");
        implementor.operationImpl();
    }
}
