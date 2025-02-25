package com.buk.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: 自定义注解 - 切面
 *
 * @author BuK
 * @since 2020/08/20
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspectAnnotation {

    String DEFAULT_VALUE = "[自定义注解]-切面: MyAspectAnnotation.value";

    String value() default DEFAULT_VALUE;
}
