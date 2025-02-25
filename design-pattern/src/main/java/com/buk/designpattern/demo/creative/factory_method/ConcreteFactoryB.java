package com.buk.designpattern.demo.creative.factory_method;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 【具体工厂】
 * - 主要是实现抽象工厂中的抽象方法，完成具体产品的创建
 *
 * @author jiangbk
 * @date 2021/3/12
 **/
@Slf4j
public class ConcreteFactoryB extends AbstractFactory {

    @Override
    Product createProduct(ProductTypeEnum productTypeEnum) {
        Objects.requireNonNull(productTypeEnum);
        log.info("具体工厂B");
        switch (productTypeEnum) {
            case PRODUCT_A:
                return new ConcreteProductA();
            case PRODUCT_B:
                return new ConcreteProductB();
            default:
                throw new RuntimeException("具体产品不存在");
        }
    }
}
