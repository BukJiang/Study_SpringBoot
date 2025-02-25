package com.buk.validator.annotation;


import com.buk.validator.validator.MyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO: 自定义注解 - 验证器
 *
 * @author BuK
 * @since 2020/08/20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {MyValidator.class}
)
public @interface MyValidationAnnotation {

    String[] values() default {};

    String message() default "[自定义注解]-[验证器]: MyValidationAnnotation.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
