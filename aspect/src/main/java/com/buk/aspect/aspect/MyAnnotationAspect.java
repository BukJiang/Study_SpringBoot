package com.buk.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * TODO: 注解切面
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@Aspect
@Component
public class MyAnnotationAspect {

    /**
     * 切入点
     */
    private static final String POINT_CUT = "@annotation(com.buk.aspect.annotation.MyAspectAnnotation)";

    /**
     * 通过 JoinPoint 参数可以获取目标方法的方法名、修饰符等信息
     *
     * @param joinPoint
     */
    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("[注解切面]-@Before: {}.{}", declaringTypeName, name);
    }

    /**
     * 通过 JoinPoint 参数可以获取目标方法的方法名、修饰符等信息
     *
     * @param joinPoint
     */
    @After(POINT_CUT)
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("[注解切面]-@After: {}.{}", declaringTypeName, name);
    }
}
