package com.makebono.mavenplayland.module_test.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName: DontAutowireMe 
 * @Description: Test for exluding beans for autowiring.
 * @author makebono
 * @date 2018年1月5日 上午8:55:39 
 *  
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DontAutowireMe {

}
