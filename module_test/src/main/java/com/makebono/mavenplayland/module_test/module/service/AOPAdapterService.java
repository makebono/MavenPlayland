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
// Interesting that Aspect will not intercept an empty sub-class using all method inherited from its superclass. So to
// add point cut in such classes, the solution is to intercept their superclass. To dig a little bit deeper, there's no
// need to add @EnableAspectJAutoProxy on the superclass, just make sure the sub-class's package has a class with such
// annotation.
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPAdapterService {
    static {
        System.out.println(AOPAdapterService.class.getName() + " initialized");
    }
}
