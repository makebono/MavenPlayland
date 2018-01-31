package com.makebono.mavenplayland.module_test.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.service.AOPTestComponent;
import com.makebono.mavenplayland.module_test.module.service.AlgorithmService;

/** 
 * @ClassName: AOPTestController 
 * @Description: Test controller for AOP feature
 * @author makebono
 * @date 2018年1月29日 下午5:14:52 
 *  
 */
@Controller
@RequestMapping("aop")
public class AOPTestController {
    private static final Logger logger = LoggerFactory.getLogger(AOPTestController.class);
    @Autowired
    private AlgorithmService service;

    @Autowired
    private AOPTestComponent component;

    @RequestMapping("/test")
    @ResponseBody
    public String testBefore(final HttpServletRequest request) throws Throwable {
        final int home = Integer.valueOf(request.getParameter("home"));
        final int target = Integer.valueOf(request.getParameter("target"));
        final int level = Integer.valueOf(request.getParameter("level"));

        logger.info("Trying to solve Tower of Hanoi puzzle, input: " + home + ", " + target + ", " + level);

        try {
            this.service.toh(home, target, level);
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
        return "Problem solved, check output file.";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String testBefore2(final HttpServletRequest request) {
        logger.info("Calling AOPTestComponent.");

        this.component.yield();
        return "Component called";
    }

    @RequestMapping("/testAround")
    @ResponseBody
    public String testAround(final HttpServletRequest request) {
        logger.info("Trying to solve Eight Queens problem.");
        final String result;
        try {
            result = this.service.eightQueens();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
        return "Eight queens solution: " + result;
    }

    @RequestMapping("/testAround2")
    @ResponseBody
    public String testAroundNextFib(final HttpServletRequest request) {
        final int n = Integer.valueOf(request.getParameter("n"));
        logger.info("Calculating n+1 Fibonacci value, n = " + n);

        final long result;
        try {
            result = this.service.fibonacci(n);
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }

        return "n = " + n + ", fib(n+1) = " + result;
    }

    // This step will be handled by 2 handler following the pre-set order. Output would be fib((n+1)*2). Easy to check
    // the correctness.
    @RequestMapping("/testAround3")
    @ResponseBody
    public String testAroundDoubleNextFib(final HttpServletRequest request) {
        final int n = Integer.valueOf(request.getParameter("n"));
        logger.info("Calculating (n+1)*2 Fibonacci value, n = " + n);

        final long result;
        try {
            result = this.service.fibonacci2(n);
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }

        return "n = " + n + ", fib((n+1)*2) = " + result;
    }

    @RequestMapping("/testReturn")
    @ResponseBody
    public String testReturn(final HttpServletRequest request) {
        final String date = request.getParameter("date");
        logger.info("Calculating day in a week of input date = " + date);

        final String result;
        try {
            result = this.service.doomsday(date);
            System.out.println(result);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return "Error occurs, please don't contact the author since it must be your fault.";
        }

        return result;
    }

}
