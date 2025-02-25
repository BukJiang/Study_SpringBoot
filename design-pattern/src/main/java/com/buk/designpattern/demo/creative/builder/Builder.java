package com.buk.designpattern.demo.creative.builder;

/**
 * 【抽象建造者】
 * - 一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()
 *
 * @author jiangbk
 * @date 2021/4/21
 **/
public abstract class Builder {

    /**
     * 【产品角色】
     */
    protected Product product = new Product();

    /**
     * 建造部件
     *
     * @return
     */
    public abstract String buildPartA();

    /**
     * 建造部件
     *
     * @return
     */
    public abstract String buildPartB();

    /**
     * 建造部件
     *
     * @return
     */
    public abstract String buildPartC();

    /**
     * 建造结果
     *
     * @return
     */
    public Product getResult() {
        product.setPartA(buildPartA());
        product.setPartB(buildPartB());
        product.setPartC(buildPartC());
        return product;
    }
}
