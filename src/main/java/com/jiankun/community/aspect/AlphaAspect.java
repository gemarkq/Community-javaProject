package com.jiankun.community.aspect;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AlphaAspect {

    @Pointcut("execution(* com.jiankun.community.service.*.*(..))")
    public void pointcut() {

    }

    // 连接点开始，连接点结束，返回值，抛异常，连接点前后结束同时
    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("after");
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("after Returning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("after throwing");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before around");
        Object obj = joinPoint.proceed();
        System.out.println("after around");
        return obj;
    }

}
