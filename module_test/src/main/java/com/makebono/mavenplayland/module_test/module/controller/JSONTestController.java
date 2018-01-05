package com.makebono.mavenplayland.module_test.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.MicroArchitecture;
import com.makebono.mavenplayland.module_test.module.service.MicroArchitectureQueryService;

/** 
 * @ClassName: JSONTestController 
 * @Description: Test on JSON 
 * @author makebono
 * @date 2018年1月5日 上午10:30:05 
 *  
 */
@SuppressWarnings(value = { "rawtypes" })
@Controller
@RequestMapping("/jsonTest")
public class JSONTestController {
    private static final Logger logger = LoggerFactory.getLogger(JSONTestController.class);

    @Autowired
    private MicroArchitectureQueryService service;

    @RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public MicroArchitecture queryById(final HttpServletRequest request, final HttpServletResponse response) {
        final String model = request.getParameter("MODEL");

        // System.out.println(id);

        logger.info("Query by model: ", model);
        try {
            final MicroArchitecture result = service.selectOne(model);
            System.out.println(result);

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/selectAll")
    public @ResponseBody List selectAll() {
        logger.info("Select all from maven_test2 ");
        try {
            final List<MicroArchitecture> result = service.selectAll();
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
