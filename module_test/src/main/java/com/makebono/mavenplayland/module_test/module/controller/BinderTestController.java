package com.makebono.mavenplayland.module_test.module.controller;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
 * @Description: Testing the customized web binding initilizer and message converter.
 * @author makebono
 * @date 2018年1月3日 上午10:31:05 
 *  
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
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
        logger.info("Invoking customized WebBindingInitializer: " + student);
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
        logger.info("Invoking customized WebBindingInitializer " + date);
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

    // When using customized converter, String will also need a converter. So don't forget to add one.
    @RequestMapping(value = "/localBindingTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String test3(@RequestParam(value = "intArray") final int[] intArray) {
        logger.info("Binding String input into int[] " + intArray);

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

    @RequestMapping(value = "/2-dArrayTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public int[][] test4() {
        try {
            final int[][] result = new int[3][3];
            final Random rng = new Random();

            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++) {
                    result[m][n] = rng.nextInt(11);
                }
            }

            /**Return format in Json:
             *     [[2,5,10],[2,5,3],[10,10,4]]
             */

            logger.info("See what would Json do with 2-d arrays: " + result);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/3-dArrayTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public int[][][] test5() {

        try {
            final int[][][] result = new int[3][3][3];
            final Random rng = new Random();

            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < 3; n++) {
                    for (int i = 0; i < 3; i++) {
                        result[m][n][i] = rng.nextInt(11);
                    }
                }
            }

            /**Return format in Json:
             *     [[[10,6,6],[8,1,6],[10,7,9]],[[10,7,1],[6,7,1],[1,5,5]],[[0,9,4],[0,9,5],[2,6,2]]]
             */

            logger.info("See what would Json do with 3-d arrays: " + result);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/mapTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map test6() {

        try {
            final Map resultMap = new HashMap();
            final int[] resultValue = new int[3];
            final Random rng = new Random();

            for (int m = 0; m < 3; m++) {
                String key = "Entry ";
                key += m;
                resultValue[m] = rng.nextInt(11);
                resultMap.put(key, resultValue[m]);
            }

            /**Return format in Json:
             *     {"Entry 1":8,"Entry 0":10,"Entry 2":3}
             */

            logger.info("Json return format of map: " + resultMap);
            return resultMap;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/listTest", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List test7() {
        logger.info("Json return format of map.");

        try {
            final List resultList = new ArrayList();
            final int[] resultValue = new int[3];
            final Random rng = new Random();

            for (int m = 0; m < 3; m++) {
                resultValue[m] = rng.nextInt(11);
                resultList.add(resultValue[m]);
            }

            /**Return format in Json, it's similar to array.
             *     [4,0,8]
             */

            return resultList;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
