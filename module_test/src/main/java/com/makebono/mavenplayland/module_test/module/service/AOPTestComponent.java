package com.makebono.mavenplayland.module_test.module.service;

import org.springframework.stereotype.Component;

import com.makebono.mavenplayland.module_test.module.entities.ComponentTestDTO;

/** 
 * @ClassName: AOPTestComponent 
 * @Description: AOPTestComponent 
 * @author makebono
 * @date 2018年1月30日 上午9:42:22 
 *  
 */
@Component
public class AOPTestComponent {
    public ComponentTestDTO yield() {
        System.out.println(this.getClass().getName() + " called.");
        return new ComponentTestDTO();
    }
}
