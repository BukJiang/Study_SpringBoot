package com.buk.aspect.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * TODO: 切面
 *
 * @author BuK
 * @since 2020/08/19
 */
@Slf4j
@Aspect
@Component
public class MyAspect {

    /**
     * 切入点
     */
    private static final String POINT_CUT = "execution(public * com.buk.aspect.service.AspectService.*(..))";

    /**
     * 通过 JoinPoint 参数可以获取目标方法的方法名、修饰符等信息
     *
     * @param joinPoint
     */
    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("[切面]-@Before: {}.{}", declaringTypeName, name);
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

        log.info("[切面]-@After: {}.{}", declaringTypeName, name);
    }

    /**
     * 通过 JoinPoint 参数可以获取目标方法的方法名、修饰符等信息
     * <p>
     * (returning = "result") 参数是指返回值的变量名，对应方法的参数
     * 注意：本样例在方法参数中定义 result 的类型为 Object，
     * 表示目标方法的返回值可以是任意类型。
     * 若 result 参数的类型为 Long，则该方法只能处理目标方法返回值为 Long 的情况。
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = POINT_CUT, returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("[切面]-@AfterReturning: {}.{}", declaringTypeName, name);
        log.info("[切面]-@AfterReturning-返回值: {}", result);
    }

    /**
     * 通过 JoinPoint 参数可以获取目标方法的方法名、修饰符等信息
     * <p>
     * (throwing = "exception") 参数是指抛出异常的变量名，对应方法的参数
     * 样例中设置的异常类型为 Exception 表示所有的异常都会进入该方法中执行。
     * 若异常类型为 RuntimeException 则表示只有目标方法抛出的 RuntimeException 异常才会进入该方法的处理。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = POINT_CUT, throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("[切面]-@AfterThrowing: {}.{}", declaringTypeName, name);
        log.info("[切面]-@AfterThrowing-异常: {}", exception.getMessage());
    }

    /**
     * 调用 ProceedingJointPoint 对象的 proceed 方法使目标方法继续执行，
     * 可以再次指定目标方法的执行参数、返回值，异常。
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around(value = POINT_CUT)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getSignature().getName();
        String declaringTypeName = proceedingJoinPoint.getSignature().getDeclaringTypeName();

        log.info("[切面]-@Around: {}.{}", declaringTypeName, name);

        try {
            // 统计方法执行时间
            long start = System.currentTimeMillis();
            Object result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("[切面]-@Around: {}，方法执行时间为: {}ms", name, (end - start));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
