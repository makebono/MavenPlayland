package com.makebono.mavenplayland.module_test.common.system.aopaspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

/** 
 * @ClassName: BonoAspectOrder2
 * @Description: Aspect handler order 2. Excecutes after order 0 done its work. So fibonacci2 will work as fib((n+1)*2)
 * @author makebono
 * @date 2018年1月29日 下午5:25:43 
 *  
 */
@Aspect
@Order(2)
public class BonoAspectOrder2 {
    static {
        System.out.println(
                "@Aspect com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoAspectOrder2 initialzed.");
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

    // Intercept all service and do something.
    @Around("execution(* com.makebono.mavenplayland.module_test.*.service.*Service.*(..))")
    public Object globalRuleCheckingAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();
        System.out.println("Number of input parameters = " + args.length);
        final Object result;

        try {
            result = joinPoint.proceed(args);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage() + "\nRequest intercepted by "
                    + this.getClass().getName() + ", service failed.");
            throw e;
        }

        System.out.println("Request intercepted by " + this.getClass().getName() + ", service correctly executed.");
        return result;
    }
}
