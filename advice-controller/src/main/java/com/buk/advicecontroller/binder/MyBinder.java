package com.buk.advicecontroller.binder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * TODO: 初始化绑定器
 * TODO: @ControllerAdvice，@RestControllerAdvice 控制器增强
 * TODO: @ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有 @RequestMapping 注解的方法。
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@RestControllerAdvice
public class MyBinder {

    /**
     * 初始化绑定器 {@link InitBinder}
     *
     * @param binder
     */
    @InitBinder
    public void execute(WebDataBinder binder) {
        log.info("[初始化绑定器]: InitBinder");

        // 对所有String类型的参数进行StringTrimmerEditor
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
