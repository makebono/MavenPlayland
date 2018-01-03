package com.makebono.mavenplayland.module_test.module.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: BinderTestController 
 * @Description: Testing the customized web binding initilizer 
 * @author makebono
 * @date 2018年1月3日 上午10:31:05 
 *  
 */
@Controller
public class BinderTestController {
    private static final Logger logger = LoggerFactory.getLogger(BinderTestController.class);

    @RequestMapping(value = "/bindingTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String test(@RequestParam(required = false, value = "student") final Student student,
            @RequestParam(required = false, value = "date") Date date, final HttpServletRequest request) {
        logger.info("Invoking customized WebBindingInitializer", student, date);
        try {
            if (date == null || request.getParameter("date") == " ") {
                date = new Date();
            }
            System.out.println(student);
            System.out.println(date);
            return student + "<br>" + date;
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }
}
