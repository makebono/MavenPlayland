package com.makebono.mavenplayland.module_test.common.system;

/** 
 * @ClassName: InitializationAndDestructionManager 
 * @Description: A simple manager to see how it works.
 * @author makebono
 * @date 2018年1月12日 下午2:27:38 
 *  
 */
public class InitializationAndDestructionManager {
    public void bonoInit() {
        System.err.println(
                "------------------------------------------------------------------------------------Customized MAKEBONO-controlled intializing------------------------------------------------------------------------------------");
    }

    public void bonoDestroy() {
        System.err.println(
                "------------------------------------------------------------------------------------Customized MAKEBONO-controlled destroying-------------------------------------------------------------------------------------");
    }
}
