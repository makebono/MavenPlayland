package com.makebono.mavenplayland.module_web.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.service.JsonProcessingService;

/** 
 * @ClassName: JSONSimpleProcessingController 
 * @Description: Test controller for JSON.simple processing
 * @author makebono
 * @date 2018年1月10日 下午3:01:35 
 *  
 */
@RequestMapping("/jsonSimpleTest")
@Controller
public class JSONSimpleProcessingController {
    private static final Logger logger = LoggerFactory.getLogger(JSONSimpleProcessingController.class);

    @Autowired
    private JsonProcessingService service;

    @RequestMapping("/write")
    @ResponseBody
    public String write(final HttpServletRequest request) {
        final String fileName = request.getParameter("path");

        logger.info("Writting file to '/outputs/" + fileName + "'");
        try {
            service.writeJsonFile(fileName);
            return "Json file wrote as " + fileName;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read(final HttpServletRequest request) {
        final String fileName = request.getParameter("path");

        logger.info("Reading file from '/outputs/" + fileName + "'");
        try {
            final String result = service.parseJsonFile(fileName);
            System.out.println(result);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
