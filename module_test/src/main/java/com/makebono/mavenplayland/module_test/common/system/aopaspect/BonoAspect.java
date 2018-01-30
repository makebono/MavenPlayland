package com.makebono.mavenplayland.module_test.common.system.aopaspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/** 
 * @ClassName: BonoAspect 
 * @Description: Aspect handler, includes methods handling @Before, @After, @Around, @AfterReturning, @AfterThrowing.
 * @author makebono
 * @date 2018年1月29日 下午5:25:43 
 *  
 */
@Aspect
public class BonoAspect {
    static {
        System.out.println(
                "@Aspect com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspect initialzed.");
    }

    @Before("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.toh(..))")
    public void printBefore(final JoinPoint joinPoint) {
        System.out.println("AOP @Before annotation on: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.toh(..))")
    public void printAfter(final JoinPoint joinPoint) {
        System.out.println("AOP @After annotation on: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.makebono.mavenplayland.module_test.module.service.AOPTestComponent.*(..))")
    public void testBefore(final JoinPoint joinPoint) {
        System.out.println("AOP @Before annotation on: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.eightQueens(..))")
    public String testAround(final ProceedingJoinPoint joinPoint) {
        System.out.println("AOP @Around annotation before: " + joinPoint.getSignature().getName());

        try {
            final String result = (String) joinPoint.proceed();
            System.out.println("AOP @Around annotation after: " + joinPoint.getSignature().getName());
            return result;
        }
        catch (final Throwable e) {
            e.printStackTrace();
            return null;
        }

    }

    @Around("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.fibonacci(..))")
    public long testAroundWithParameter(final ProceedingJoinPoint joinPoint) {
        System.out.println("AOP @Around annotation before: " + joinPoint.getSignature().getName());

        try {
            final Object[] args = joinPoint.getArgs();
            System.out.println("Initial input n = " + Arrays.toString(args));
            args[0] = (int) (args[0]) + 1;
            final long result = (long) joinPoint.proceed(args);
            System.out.println("Pointcut intercepted, fib(n+1) = " + result);
            System.out.println("AOP @Around annotation after: " + joinPoint.getSignature().getName());
            return result;

        }
        catch (final Throwable e) {
            e.printStackTrace();
            return -1;
        }
    }

    @AfterReturning("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.doomsday(..))")
    public void testAfterReturning(final JoinPoint joinPoint) {
        System.out.println("AOP @AfterReturning annotation: " + joinPoint.getSignature().getName()
                + "\nRequest is successfully served.");
    }

    @AfterThrowing("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.doomsday(..))")
    public void testAfterThrowing(final JoinPoint joinPoint) {
        System.out.println("AOP @AfterThrowing annotation: " + joinPoint.getSignature().getName()
                + "\nRequest failed, exception is threw out.");
    }
}
