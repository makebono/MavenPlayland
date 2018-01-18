package com.makebono.mavenplayland.module_test.common.event;

import org.springframework.context.ApplicationEvent;

/** 
 * @ClassName: TestEvent 
 * @Description: An event
 * @author makebono
 * @date 2018年1月18日 上午9:02:37 
 *  
 */
public class TestEvent extends ApplicationEvent {
    private static final long serialVersionUID = -7043055293675015082L;
    private final String message;

    public TestEvent(final String message) {
        super(message);
        this.message = message;
    }

    public void initEvent() {
        System.out.println(
                "------------------------------------------------------------------------------------Event created.------------------------------------------------------------------------------------");
        System.out.println("New event: =====>> " + message + " <<=====");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
