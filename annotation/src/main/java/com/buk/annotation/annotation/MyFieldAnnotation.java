package com.buk.annotation.annotation;

import java.lang.annotation.*;

/**
 * TODO: 自定义注解 - field
 *
 * @author BuK
 * @see com.buk.annotation.annotation.MyAnnotationTest
 * @since 2020/08/20
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFieldAnnotation {

    String DEFAULT_VALUE = "[自定义注解]-field: MyFieldAnnotation";

    String value() default DEFAULT_VALUE;
}
