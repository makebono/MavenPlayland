package com.makebono.mavenplayland.module_test.module.service;

import org.springframework.stereotype.Service;

/** 
 * @ClassName: DummyService 
 * @Description: Empty service class, don't autowire it.
 * @author makebono
 * @date 2018年1月5日 上午8:59:51 
 *  
 */
// Will throw NoSuchBeanDefinitionException
// @DontAutowireMe
@Service
public class DummyService {

}
