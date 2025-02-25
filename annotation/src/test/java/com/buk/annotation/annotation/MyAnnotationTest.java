package com.buk.annotation.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

/**
 * TODO: 自定义注解测试
 */
@SpringBootTest
@MyTypeAnnotation
class MyAnnotationTest {

    @MyFieldAnnotation
    private String field = "[自定义注解测试] - field";

    @Test
    void type() {
        Class<MyAnnotationTest> myAnnotationTestClass = MyAnnotationTest.class;
        boolean isAnnotationPresent = myAnnotationTestClass.isAnnotationPresent(MyTypeAnnotation.class);
        System.out.println("[自定义注解测试]-Class.isAnnotationPresent: " + isAnnotationPresent);
        if (isAnnotationPresent) {
            MyTypeAnnotation myTypeAnnotation = myAnnotationTestClass.getAnnotation(MyTypeAnnotation.class);
            System.out.println("[自定义注解测试]-myTypeAnnotation.value: " + myTypeAnnotation.value());
        }
    }

    @Test
    void field() throws NoSuchFieldException {
        Class<MyAnnotationTest> myAnnotationTestClass = MyAnnotationTest.class;
        Field field = myAnnotationTestClass.getDeclaredField("field");
        boolean isAnnotationPresent = field.isAnnotationPresent(MyFieldAnnotation.class);
        System.out.println("[自定义注解测试]-Field.isAnnotationPresent: " + isAnnotationPresent);
        if (isAnnotationPresent) {
            MyFieldAnnotation myFieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);
            System.out.println("[自定义注解测试]-myFieldAnnotation.value: " + myFieldAnnotation.value());
        }
    }
}