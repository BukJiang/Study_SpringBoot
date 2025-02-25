package com.buk.designpattern.demo.creative.simple_factory;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 【具体产品】
 * - 是简单工厂模式的创建目标
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
@Slf4j
public class ConcreteProductB implements Product {

    /**
     * 具体产品枚举
     */
    private ProductTypeEnum productTypeEnum;

    public ConcreteProductB(ProductTypeEnum productTypeEnum) {
        Objects.requireNonNull(productTypeEnum);
        this.productTypeEnum = productTypeEnum;
    }

    @Override
    public void use() {
        log.info(productTypeEnum.description);
    }
}
