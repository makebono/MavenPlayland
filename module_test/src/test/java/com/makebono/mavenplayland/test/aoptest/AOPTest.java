package com.makebono.mavenplayland.test.aoptest;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.makebono.mavenplayland.module_test.module.service.AOPTestComponent;
import com.makebono.mavenplayland.module_test.module.service.AlgorithmService;

/** 
 * @ClassName: AOPTest 
 * @Description: AOPTest
 * @author makebono
 * @date 2018年1月30日 上午10:06:00 
 *  
 */
public class AOPTest {
    private static ApplicationContext context;
    static {
        context = new ClassPathXmlApplicationContext("com/makebono/mavenplayland/test/testconfigurations/aopTest.Xml");
    }

    public static void main(final String args[]) throws IOException {
        test1();
    }

    public static void test1() {
        final AOPTestComponent component = context.getBean(AOPTestComponent.class);
        component.yield();
    }

    public static void test2() throws IOException {
        final AlgorithmService service = context.getBean(AlgorithmService.class);
        service.toh(1, 3, 3);
    }
}
