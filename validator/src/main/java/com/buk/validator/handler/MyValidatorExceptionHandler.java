package com.buk.validator.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: 统一异常处理器
 * TODO: @ControllerAdvice，@RestControllerAdvice 控制器增强
 * TODO: @ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有 @RequestMapping 注解的方法。
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@RestControllerAdvice
public class MyValidatorExceptionHandler {

    /**
     * 异常处理器 {@link ExceptionHandler}: 作用是当出现其定义的异常时进行处理的方法
     * 响应状态码 {@link ResponseStatus}
     *
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void execute(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<String> allErrorMessageList = allErrors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.info("[统一异常处理]-MethodArgumentNotValidException: " + allErrorMessageList);
    }
}
