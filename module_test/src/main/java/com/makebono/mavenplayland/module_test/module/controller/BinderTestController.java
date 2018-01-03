package com.makebono.mavenplayland.module_test.module.controller;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
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
        binder.registerCustomEditor(Arrays.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(final String input) {
                logger.debug("Convert input into int[].");
                try {
                    System.out.println("Read a String input, convert it into int[].");
                    final int[] result = new int[input.length()];
                    int i = 0;
                    for (final char cursor : input.toCharArray()) {
                        result[i++] = Character.digit(cursor, 10);
                    }

                    setValue(result);
                }
                catch (final Exception e) {
                    System.out.println(
                            "Error occurs, please follow the input format: 'FirstName LastName(id) from University'. For more details: "
                                    + e.getMessage());
                }
            }
        });
    }

    @RequestMapping(value = "/globalBindingTest", method = { RequestMethod.POST, RequestMethod.GET })
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

    @RequestMapping(value = "/localBindingTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String test2(@RequestParam(value = "intArray") final int[] intArray) {
        logger.info("Binding String input into int[]" + intArray);

        try {
            final StringBuilder sb = new StringBuilder();
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
