package com.buk.designpattern.demo.creative.abstract_factory;

/**
 * 产品类型枚举
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public enum ProductBTypeEnum {

    //
    PRODUCT_B1("产品B1"),
    PRODUCT_B2("产品B2");

    public final String description;

    ProductBTypeEnum(String description) {
        this.description = description;
    }
}
