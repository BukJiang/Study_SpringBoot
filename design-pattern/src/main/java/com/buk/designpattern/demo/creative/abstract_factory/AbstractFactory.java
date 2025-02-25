package com.buk.designpattern.demo.creative.abstract_factory;

/**
 * 【抽象工厂】
 * - 提供了创建产品的接口，它包含多个创建产品的方法 createProduct()，可以创建多个不同等级的产品
 *
 * @author jiangbk
 * @date 2021/3/12
 **/
public interface AbstractFactory {

    /**
     * 使用示例
     *
     * @param args
     */
    static void main(String[] args) {
        ProductA productA;
        ProductB productB;

        AbstractFactory concreteFactoryA = new ConcreteFactoryA();
        productA = concreteFactoryA.createProductA(ProductATypeEnum.PRODUCT_A1);
        productA.show();
        productB = concreteFactoryA.createProductB(ProductBTypeEnum.PRODUCT_B1);
        productB.show();

        AbstractFactory concreteFactoryB = new ConcreteFactoryB();
        productA = concreteFactoryB.createProductA(ProductATypeEnum.PRODUCT_A2);
        productA.show();
        productB = concreteFactoryB.createProductB(ProductBTypeEnum.PRODUCT_B2);
        productB.show();
    }

    /**
     * 创建产品
     *
     * @param productTypeEnum
     * @return
     */
    ProductA createProductA(ProductATypeEnum productTypeEnum);

    /**
     * 创建产品
     *
     * @param productTypeEnum
     * @return
     */
    ProductB createProductB(ProductBTypeEnum productTypeEnum);
}
