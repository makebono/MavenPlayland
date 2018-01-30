package com.makebono.mavenplayland.module_test.common.system.aopaspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/** 
 * @ClassName: BonoAspect 
 * @Description: Aspect handler order 1. Excecutes after order 0 done its work. So fibonacci2 will work as fib((n+1)*2)
 * @author makebono
 * @date 2018年1月29日 下午5:25:43 
 *  
 */
@Aspect
@Order(1)
public class BonoAspectOrder1 {
    static {
        System.out.println(
                "@Aspect com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspectOrder1 initialzed.");
    }

    @Around("execution(* com.makebono.mavenplayland.module_test.module.service.AlgorithmService.fibonacci2(..))")
    public long testAroundWithParameter2(final ProceedingJoinPoint joinPoint) {
        try {
            final Object[] args = joinPoint.getArgs();
            System.out.println("Initial input n = " + Arrays.toString(args));
            args[0] = (int) (args[0]) * 2;
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

}
