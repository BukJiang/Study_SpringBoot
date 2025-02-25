package com.buk.validator.validator;

import com.buk.validator.annotation.MyValidationAnnotation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * TODO: 约束验证器
 *
 * @author BuK
 * @since 2020/08/20
 */
@Slf4j
public class MyValidator implements ConstraintValidator<MyValidationAnnotation, Object> {

    @Override
    public void initialize(MyValidationAnnotation constraintAnnotation) {
        log.info("[约束验证器]-initialize: {}", constraintAnnotation.message());
        log.info("[约束验证器]-initialize: {}", Arrays.toString(constraintAnnotation.values()));
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        log.info("[约束验证器]-isValid: {}", o);

        return true;
    }
}
