package com.makebono.mavenplayland.module_test.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.service.DoomsdayService;

/** 
 * @ClassName: InterceptorTestController 
 * @Description: Controller for interceptor test
 * @author makebono
 * @date 2018年1月29日 下午2:13:13 
 *  
 */
@Controller
@RequestMapping("/interceptorTest")
public class InterceptorTestController {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorTestController.class);

    @Autowired
    private DoomsdayService service;

    @RequestMapping("/date")
    @ResponseBody
    public String doomsday(final HttpServletRequest request) {
        final String date = (String) request.getAttribute("date");

        logger.info("Querying current date: " + date);
        try {
            final String dayInWeek = this.service.doomsday(date);
            System.out.println(dayInWeek);
            return dayInWeek;
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }
}
