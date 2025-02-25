package com.buk.designpattern.demo.structural.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * 【子系统】
 * - 实现系统的部分功能，客户可以通过外观角色访问它
 *
 * @author jiangbk
 * @date 2021/4/20
 **/
@Slf4j
public class SubSystemA {

    /**
     * 执行
     */
    public void execute() {
        log.info("[调用]子系统A");
    }
}
