package com.buk.designpattern.demo.creative.factory_method;

import lombok.extern.slf4j.Slf4j;

/**
 * 【具体产品】
 * - 实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应
 *
 * @author jiangbk
 * @date 2021/3/12
 **/
@Slf4j
public class ConcreteProductA implements Product {

    @Override
    public void show() {
        log.info("具体产品A\n");
    }
}
