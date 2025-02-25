package com.buk.designpattern.demo.creative.abstract_factory;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 【具体工厂】
 * - 主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建
 *
 * @author jiangbk
 * @date 2021/3/12
 **/
@Slf4j
public class ConcreteFactoryB implements AbstractFactory {

    @Override
    public ProductA createProductA(ProductATypeEnum productTypeEnum) {
        Objects.requireNonNull(productTypeEnum);
        log.info("具体工厂B");
        switch (productTypeEnum) {
            case PRODUCT_A1:
                return new ConcreteProductA1();
            case PRODUCT_A2:
                return new ConcreteProductA2();
            default:
                throw new RuntimeException("具体产品不存在");
        }
    }

    @Override
    public ProductB createProductB(ProductBTypeEnum productTypeEnum) {
        Objects.requireNonNull(productTypeEnum);
        log.info("具体工厂B");
        switch (productTypeEnum) {
            case PRODUCT_B1:
                return new ConcreteProductB1();
            case PRODUCT_B2:
                return new ConcreteProductB2();
            default:
                throw new RuntimeException("具体产品不存在");
        }
    }
}
