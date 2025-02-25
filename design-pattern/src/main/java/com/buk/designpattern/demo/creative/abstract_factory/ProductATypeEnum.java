package com.buk.designpattern.demo.creative.abstract_factory;

/**
 * 产品类型枚举
 *
 * @author jiangbk
 * @date 2021/3/10
 **/
public enum ProductATypeEnum {

    //
    PRODUCT_A1("产品A1"),
    PRODUCT_A2("产品A2");

    public final String description;

    ProductATypeEnum(String description) {
        this.description = description;
    }
}
