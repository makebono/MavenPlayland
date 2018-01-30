/**Note:
 * It's important to know the format of Point cut.
 * For example, 
 *     @After("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.toh(..))")
 *     -> first * means any return type.
 *     -> (..) as .toh's parameter means any parameter parsed in.
 *     -> If wrote as AlgorithmService.*(..), it means any method in the class. Same idea for * at class/package layer.
 *     
 * Annotation @Order(n) decides order of Aspects to be execured.
 * 
 * joinPoint.proceed(args) works recursively.  
 * For example, both of the method testAroundWithParameter2() here(order(0)) and in order(1), they print the value of n after
 * handling. It works like below:
 *     Order(0)
 *     print: Initial input n = [6]
 *     parse: (6+1)
 *         Order(1)
 *         print: Initial input n = [7]
 *         print: Request intercepted by com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspectOrder1, value of n after handling: 14
 *         return: fib(7*2)
 *     Order(0)                   
 *     print: Request intercepted by com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspectOrder0, value of n after handling: 7
 *     return: fib(7*2)  

 */
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
import org.springframework.core.annotation.Order;

/** 
 * @ClassName: BonoAspect 
 * @Description: Aspect handler, includes methods handling @Before, @After, @Around, @AfterReturning, @AfterThrowing.
 * @author makebono
 * @date 2018年1月29日 下午5:25:43 
 *  
 */
@Aspect
@Order(0)
public class BonoAspectOrder0 {
    static {
        System.out.println(
                "@Aspect com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspectOrder0 initialzed.");
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

    @Around("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.fibonacci2(..))")
    public long testAroundWithParameter2(final ProceedingJoinPoint joinPoint) {

        try {
            final Object[] args = joinPoint.getArgs();
            System.out.println("Initial input n = " + Arrays.toString(args));
            args[0] = (int) (args[0]) + 1;
            final long result = (long) joinPoint.proceed(args);
            System.out.println(
                    "Request intercepted by " + this.getClass().getName() + ", value of n after handling: " + args[0]);
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
