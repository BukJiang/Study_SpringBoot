package com.buk.designpattern.demo.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体外观】
 * - 为多个子系统对外提供一个共同的接口
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class ConcreteFacade implements Facade {

    /**
     * 子系统A
     */
    private SubSystemA subSystemA = new SubSystemA();

    /**
     * 子系统B
     */
    private SubSystemB subSystemB = new SubSystemB();

    /**
     * 执行
     */
    @Override
    public void execute() {
        log.info("[执行]外观系统");
        subSystemA.execute();
        subSystemB.execute();
    }
}
