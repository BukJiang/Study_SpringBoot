package com.buk.designpattern.demo.creative.simple_factory;

import java.util.Objects;

/**
 * 【简单工厂】
 * - 是简单工厂模式的核心，负责实现创建所有实例的内部逻辑
 * - 工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public class SimpleFactory {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Product productA = SimpleFactory.getProduct(ProductTypeEnum.PRODUCT_A);
        productA.use();

        Product productB = SimpleFactory.getProduct(ProductTypeEnum.PRODUCT_B);
        productB.use();
    }

    private SimpleFactory() {
    }

    /**
     * 获取具体产品
     *
     * @param productTypeEnum
     * @return
     */
    public static Product getProduct(ProductTypeEnum productTypeEnum) {
        Objects.requireNonNull(productTypeEnum);
        switch (productTypeEnum) {
            case PRODUCT_A:
                return new ConcreteProductA(productTypeEnum);
            case PRODUCT_B:
                return new ConcreteProductB(productTypeEnum);
            default:
                throw new RuntimeException("具体产品不存在");
        }
    }
}
