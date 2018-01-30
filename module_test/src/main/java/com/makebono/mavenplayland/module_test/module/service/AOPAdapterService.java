package com.makebono.mavenplayland.module_test.module.service;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: AOPAdapterService 
 * @Description: AOPAdapterService
 * @author makebono
 * @date 2018年1月30日 上午10:47:26 
 *  
 */
// For using AspectJ to intercept in web controller, this empty class could help all class in same package for enabling
// the auto proxy. In such case, it's mandatory to add @EnableAspectJAutoProxy in a package with pointcuts.
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPAdapterService {
    static {
        System.out.println(AOPAdapterService.class.getName() + " initialized");
    }
}
