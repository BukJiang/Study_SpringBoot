package com.buk.annotation.annotation;

import java.lang.annotation.*;

/**
 * TODO: 自定义注解 - type
 *
 * @author BuK
 * @see com.buk.annotation.annotation.MyAnnotationTest
 * @since 2020/08/20
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTypeAnnotation {

    String DEFAULT_VALUE = "[自定义注解]-type: MyTypeAnnotation";

    String value() default DEFAULT_VALUE;
}
