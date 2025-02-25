package com.buk.designpattern.demo.creative.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体建造者】
 * - 实现 Builder 接口，完成复杂产品的各个部件的具体创建方法
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class ConcreteBuilder extends Builder {

    /**
     * 建造部件
     *
     * @return
     */
    @Override
    public String buildPartA() {
        log.info("[具体建造者]-建造A1");
        return "A1";
    }

    /**
     * 建造部件
     *
     * @return
     */
    @Override
    public String buildPartB() {
        log.info("[具体建造者]-建造B1");
        return "B1";
    }

    /**
     * 建造部件
     *
     * @return
     */
    @Override
    public String buildPartC() {
        log.info("[具体建造者]-建造C1");
        return "C1";
    }
}
