package com.buk.designpattern.demo.creative.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * 【产品角色】
 * - 包含多个组成部件的复杂对象，由具体建造者来创建其各个零部件
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
@Slf4j
public class Product {

    /**
     * 部件
     */
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    /**
     * 展示
     */
    public void show() {
        log.info("[产品角色] partA:{}, partB:{}, partC:{}", partA, partB, partC);
    }
}
