package com.makebono.mavenplayland.module_web.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.common.event.TestEvent;

/** 
 * @ClassName: EventListenerController 
 * @Description: Test controller on event and listener.
 * @author makebono
 * @date 2018年1月18日 上午9:27:14 
 *  
 */
@Controller
@RequestMapping("/testEvent")
public class EventListenerController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/newEvent", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String initEvent(final HttpServletRequest request) {
        final String message = request.getParameter("event");
        context.publishEvent(new TestEvent(message));
        return message;
    }

    // After closing context, console would not react to any of the method in this class except the refresh method.
    @RequestMapping(value = "/close", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String closeEvent(final HttpServletRequest request) {
        final String message = "Testing '/close'";
        ((ConfigurableApplicationContext) context).close();
        return message;
    }

    // Would not stop other method.
    @RequestMapping(value = "/stop", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String stopEvent(final HttpServletRequest request) {
        final String message = "Testing '/stop'";
        ((ConfigurableApplicationContext) context).stop();
        return message;
    }

    // Reload context.
    @RequestMapping(value = "/refresh", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String refreshEvent(final HttpServletRequest request) {
        final String message = "Testing '/refresh'";
        ((ConfigurableApplicationContext) context).refresh();
        return message;
    }

    // Won't be able to start closed context.
    @RequestMapping(value = "/start", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String initializeEvent(final HttpServletRequest request) {
        final String message = "Testing '/start'";
        ((ConfigurableApplicationContext) context).start();
        return message;
    }
}
