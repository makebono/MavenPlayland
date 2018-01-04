package com.makebono.mavenplayland.module_test.module.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @InitBinder
    public void intArrayBinder(final WebDataBinder binder) {
        // Don't use Arrays.class here, or it will been store to an int[1]. So when input string > Integer.Max, it will
        // trigger error.
        binder.registerCustomEditor(int[].class, new PropertyEditorSupport() {
            @Override
            public void setAsText(final String input) {
                try {
                    final int[] result = new int[input.length()];
                    int i = 0;
                    for (final char cursor : input.toCharArray()) {
                        // Use Character.digit(char ch, int radix) for only accepting numerical input. When a letter
                        // input, .digit() returns -1.
                        // getNumericValue accepts letters input, returning their Unicode. For example 'a' = 10.
                        result[i++] = Character.getNumericValue(cursor);
                    }
                    setValue(result);
                }
                catch (final Exception e) {
                    System.out.println("Error occurs, message: " + e.getMessage());
                }
            }
        });
    }

    @RequestMapping(value = "/globalBindingTestStudent", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Student test(@RequestParam(required = false, value = "student") final Student student,
            final HttpServletRequest request) {
        logger.info("Invoking customized WebBindingInitializer", student);
        try {
            System.out.println(student);
            return student;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/globalBindingTestDate", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Date test2(@RequestParam(required = false, value = "date") Date date, final HttpServletRequest request) {
        logger.info("Invoking customized WebBindingInitializer", date);
        try {
            if (date == null || request.getParameter("date") == " ") {
                date = new Date();
            }

            System.out.println(date);
            return date;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/localBindingTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String test3(@RequestParam(value = "intArray") final int[] intArray) {
        logger.info("Binding String input into int[]" + intArray);

        try {
            final StringBuilder sb = new StringBuilder(
                    String.format("Length of input int[]: %d.<br>", intArray.length));
            for (final int i : intArray) {
                sb.append(i);
            }
            System.out.println(sb.toString());
            return sb.toString();
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }

    }
}
