package com.makebono.mavenplayland.module_test.module.controller;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private JsonProcessingService service;

    @RequestMapping("/write")
    @ResponseBody
    public String write(final HttpServletRequest request) {
        final String fileName = request.getParameter("path");
        service.writeJsonFile(fileName);

        return "Json file wrote as " + fileName;
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read(final HttpServletRequest request) {
        final String fileName = request.getParameter("path");
        final String result = service.parseJsonFile(fileName);
        System.out.println(result);
        return result;
    }
}
