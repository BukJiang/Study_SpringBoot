package com.buk.designpattern.demo.creative.simple_factory;

/**
 * 产品类型枚举
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public enum ProductTypeEnum {

    //
    PRODUCT_A("产品A"),
    PRODUCT_B("产品B");

    public final String description;

    ProductTypeEnum(String description) {
        this.description = description;
    }
}
