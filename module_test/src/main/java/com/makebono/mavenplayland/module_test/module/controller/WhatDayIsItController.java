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
 * @ClassName: WhatDayIsItController 
 * @Description: Simply add my other works into my spring project.
 * @author makebono
 * @date 2018年1月22日 上午11:21:41 
 *  
 */
@Controller
public class WhatDayIsItController {
    private static final Logger logger = LoggerFactory.getLogger(WhatDayIsItController.class);

    @Autowired
    private DoomsdayService service;

    @RequestMapping("/doomsdayTest")
    @ResponseBody
    public String doomsday(final HttpServletRequest request) {
        final String date = request.getParameter("date");

        logger.info("Acquiring date: " + date);
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
