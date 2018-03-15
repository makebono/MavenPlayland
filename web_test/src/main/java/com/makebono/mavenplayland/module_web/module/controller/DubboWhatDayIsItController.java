package com.makebono.mavenplayland.module_web.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.makebono.mavenplayland.module_test.module.dubbointerface.IDoomsdayDubboService;

/** 
 * @ClassName: WhatDayIsItController 
 * @Description: Simply add my other works into my spring project.
 * @author makebono
 * @date 2018年1月22日 上午11:21:41 
 *  
 */
@Controller
public class DubboWhatDayIsItController {
    private static final Logger logger = LoggerFactory.getLogger(DubboWhatDayIsItController.class);

    @Reference(interfaceClass = IDoomsdayDubboService.class)
    private IDoomsdayDubboService service;

    @RequestMapping("/doomsdayTest")
    @ResponseBody
    public String doomsday(final HttpServletRequest request) {
        final String date = request.getParameter("date");
        String[] inputDate;
        if (date == null) {
            inputDate = new String[0];
        } else {
            inputDate = new String[1];
            inputDate[0] = date;
        }
        try {
            final String dayInWeek = this.service.calcDoomsday(inputDate);
            logger.info("Calculation complete,\n    " + dayInWeek);
            return dayInWeek;
        }
        catch (final Throwable e) {
            logger.info("Error occurs, message: " + e.getMessage());
            return "Error occurs, message: " + e.getMessage();
        }
    }
}
