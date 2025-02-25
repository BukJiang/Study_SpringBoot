package com.buk.designpattern.demo.creative.simple_factory;

/**
 * 【抽象产品】
 * - 是简单工厂创建的所有对象的父类，负责描述所有实例共有的公共接口
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public interface Product {

    /**
     * 公共接口
     */
    void use();
}
