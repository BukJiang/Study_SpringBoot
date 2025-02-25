package com.buk.interceptor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: 自定义注解 - 拦截器
 *
 * @author BuK
 * @since 2020/08/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyInterceptorAnnotation {

    String DEFAULT_VALUE = "[自定义注解]-[拦截器]: MyInterceptorAnnotation.value";

    String value() default DEFAULT_VALUE;
}
