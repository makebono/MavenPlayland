package com.makebono.mavenplayland.module_test.common.system.aopaspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.makebono.mavenplayland.module_test.module.service.AuthorizingService;

/** 
 * @ClassName: BonoSecurityAspect 
 * @Description: BonoSecurityAspect 
 * @author makebono
 * @date 2018年2月8日 下午2:30:03 
 *  
 */
@Aspect
@Order(0)
public class BonoSecurityAspect {
    @Autowired
    private AuthorizingService authorizingService;

    static {
        System.out.println(
                "@Aspect com.makebono.mavenplayland.module_test.common.system.aopaspect.BonoSecurityAspect initialzed.");
    }

    @Around("execution(* com.makebono.mavenplayland.module_test.module.controller.TempAccountTestController.getInfo(..))")
    public Object permissionInspect(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            if (authorizingService.inspect("/securityInfo")) {
                return joinPoint.proceed();
            }
            System.out.println("User is not permitted for this manipulation.");
            return "User is not permitted for this manipulation.";
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

}
