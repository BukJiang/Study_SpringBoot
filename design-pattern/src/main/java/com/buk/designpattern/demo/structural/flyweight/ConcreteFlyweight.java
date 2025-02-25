package com.buk.designpattern.demo.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体享元】
 * - 实现抽象享元角色中所规定的接口
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class ConcreteFlyweight implements Flyweight {

    /**
     * key
     */
    private String key;

    public ConcreteFlyweight(String key) {
        this.key = key;
    }

    /**
     * 操作
     *
     * @param unsharedConcreteFlyweight
     */
    @Override
    public void operation(UnsharedConcreteFlyweight unsharedConcreteFlyweight) {
        log.info("[具体享元]key:{}, 被使用", key);
        log.info("[具体享元]使用非享元信息:{}", unsharedConcreteFlyweight.getInfo());
    }
}
