package com.makebono.mavenplayland.module_test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: SpringTestController 
 * @Description: Controller for messing with Spring
 * @author makeboon
 * @date 2017年11月30日 上午11:56:52 
 *  
 */
@Controller
@RequestMapping("/springTest")
public class SpringTestController {

    @RequestMapping("/module_index")
    public ModelAndView testA() {
        System.out.println("bonoland/module_index");

        final Map<String, String> result = new HashMap<String, String>();
        result.put("Message", "module_index");
        final ModelAndView modelView = new ModelAndView("module_index");
        modelView.addObject(result);

        return modelView;
    }

    @RequestMapping("/urlCall/{call}")
    public ModelAndView testB(@PathVariable("call") final String input) {
        System.out.println("bonoland/urlCall");
        System.out.println("You url request: " + input);

        final ModelAndView modelView = new ModelAndView("module_index");
        modelView.addObject("Message", "urlCall");
        return modelView;
    }

    // Means any input at the * would equally leads to this /starTest.
    @RequestMapping("/*/starTest")
    public String testC() {
        System.out.println("bonoland/*/starTest");
        return "/module_index";
    }

    // Notice the parameters, "from" is mandatory. @ResponseBody means this call will not leads to any url but will
    // print the output directly on browser page.
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testD(@RequestParam(required = false, value = "name") final String name,
            @RequestParam("from") final String from) {
        System.out.println("bonoland/testResponseBody");
        System.out.println(name + " from " + from);

        return "bonoland/testResponseBody<br>" + name + "  from " + from;
    }

    // Notice restrictions here, v1 must equals with "Akebono", v2 must exists but does not have restrictions on value,
    // v3 must not be put in.
    @RequestMapping(value = "/testHttpServletRequest", params = { "v1=Akebono", "v2", "!v3" })
    @ResponseBody
    public String testE(final HttpServletRequest request) {
        System.out.println("testControl/testHttpServletRequest");
        System.out.println(request.getParameter("v1") + " " + request.getParameter("v2"));

        request.setAttribute("message", "testHttpServletRequest");
        return "bonoland/testHttpServletRequest<br>" + request.getParameter("v1") + " " + request.getParameter("v2");
    }

    // HttpServletRequest uses map for parameters' names and their values.
    @RequestMapping(value = "/testRequestMethod", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String testF(final HttpServletRequest request) {
        System.out.println("testReqeustMethod");
        final String key = request.getParameterNames().nextElement();
        final String value = request.getParameter(key);
        System.out.println(key + "(" + value + ")");

        return "/module_index<br>" + "Parameter name: " + key + "<br>Parameter value: " + value;
    }
}
