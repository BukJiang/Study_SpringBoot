package com.buk.designpattern.demo.structural.flyweight;

/**
 * 【非享元】
 * - 是不可以共享的外部状态，它以参数的形式注入具体享元的相关方法中
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public class UnsharedConcreteFlyweight {

    /**
     * 信息
     */
    private String info;

    UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
