package com.makebono.mavenplayland.module_test.common.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.event.TestEvent;

/** 
 * @ClassName: TestListener 
 * @Description: Listener reacts to events.
 * @author makebono
 * @date 2018年1月18日 上午9:07:15 
 *  
 */
@Service("listnerTest")
public class TestListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        if (event instanceof TestEvent) {
            System.err.println(
                    "------------------------------------------------------------------------------------Event created.------------------------------------------------------------------------------------");
            System.err.println("=====>> New TestEvent created. <<=====");
            ((TestEvent) event).initEvent();
            System.err.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        // Triggered on closing context.
        if (event instanceof ContextClosedEvent) {
            System.err.println(
                    "------------------------------------------------------------------------------------Event triggered.------------------------------------------------------------------------------------");
            System.err.println("=====>> Context closed <<=====");
            System.err.println((ContextClosedEvent) event);
            System.err.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        // Triggered on refreshing context.
        if (event instanceof ContextRefreshedEvent) {
            System.err.println(
                    "------------------------------------------------------------------------------------Event triggered.------------------------------------------------------------------------------------");
            System.err.println("=====>> Context refreshed <<=====");
            System.err.println((ContextRefreshedEvent) event);
            System.err.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        // Triggered on initializing context.
        if (event instanceof ContextStartedEvent) {
            System.err.println(
                    "------------------------------------------------------------------------------------Event triggered.------------------------------------------------------------------------------------");
            System.err.println("=====>> Context started <<=====");
            System.err.println((ContextStartedEvent) event);
            System.err.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        // Triggered on stopping context.
        if (event instanceof ContextStoppedEvent) {
            System.err.println(
                    "------------------------------------------------------------------------------------Event triggered.------------------------------------------------------------------------------------");
            System.err.println("=====>> Context stopped <<=====");
            System.err.println((ContextStoppedEvent) event);
            System.err.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}
