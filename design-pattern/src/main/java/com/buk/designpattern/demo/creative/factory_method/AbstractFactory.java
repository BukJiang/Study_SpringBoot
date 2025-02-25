package com.buk.designpattern.demo.creative.factory_method;

/**
 * 【抽象工厂】
 * - 提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法 createProduct() 来创建产品
 *
 * @author jiangbk
 * @date 2021/3/12
 **/
abstract class AbstractFactory {

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Product product;

        AbstractFactory concreteFactoryA = new ConcreteFactoryA();
        product = concreteFactoryA.createProduct(ProductTypeEnum.PRODUCT_A);
        product.show();
        product = concreteFactoryA.createProduct(ProductTypeEnum.PRODUCT_B);
        product.show();

        AbstractFactory concreteFactoryB = new ConcreteFactoryB();
        product = concreteFactoryB.createProduct(ProductTypeEnum.PRODUCT_A);
        product.show();
        product = concreteFactoryB.createProduct(ProductTypeEnum.PRODUCT_B);
        product.show();
    }

    /**
     * 创建产品
     *
     * @param productTypeEnum
     * @return
     */
    abstract Product createProduct(ProductTypeEnum productTypeEnum);

    /**
     * 产品展示
     *
     * @param productTypeEnum
     */
    protected void show(ProductTypeEnum productTypeEnum) {
        Product product = createProduct(productTypeEnum);
        product.show();
    }
}
