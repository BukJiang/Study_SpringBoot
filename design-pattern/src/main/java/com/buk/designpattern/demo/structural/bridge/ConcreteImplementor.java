package com.buk.designpattern.demo.structural.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体实现化】
 * - 给出实现化角色接口的具体实现
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class ConcreteImplementor implements Implementor {

    /**
     * 操作实现
     */
    @Override
    public void operationImpl() {
        log.info("[具体实现化]操作实现");
    }
}
