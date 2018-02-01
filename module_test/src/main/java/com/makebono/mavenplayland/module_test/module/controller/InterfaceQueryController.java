package com.makebono.mavenplayland.module_test.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.Student;
import com.makebono.mavenplayland.module_test.module.service.DummyService;
import com.makebono.mavenplayland.module_test.module.service.InterfaceQueryService;

/** 
 * @ClassName: InterfaceQueryController 
 * @Description: Controller for testing query by Annotated Interface. Different autowiring modes lying below, check them out.
 * @author makebono
 * @date 2017年12月4日 上午8:55:27 
 *  
 */
@SuppressWarnings(value = { "unused", "rawtypes" })
@Controller
@RequestMapping("/interfaceTest")
public class InterfaceQueryController {
    private static final Logger logger = LoggerFactory.getLogger(InterfaceQueryController.class);

    // Property based autowiring
    @Autowired
    private InterfaceQueryService service;

    @Autowired
    private DummyService fakeService;

    // Constructor based autowiring
    /*@Autowired
    public InterfaceQueryController(final InterfaceQueryService service) {
        System.out.println("Constructor based autowiring");
        this.service = service;
    }
    
    // Setter based autowiring
    @Autowired
    public void setService(final InterfaceQueryService service) {
        System.out.println("Setter based autowiring");
        this.service = service;
    }
    */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    public String queryById(final HttpServletRequest request) {
        final String id = request.getParameter("id");

        // System.out.println(id);

        logger.info("Query by id: " + id);
        try {
            final Student result = service.selectById(id);
            System.out.println(result);

            return result.toString();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public List selectAll() {
        logger.info("Select all from maven_test ");
        try {
            final List<Student> result = service.selectAll();
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/selectOneFrom", method = { RequestMethod.POST })
    @ResponseBody
    public String selectOneFrom(final HttpServletRequest request) {
        final String id = "'" + request.getParameter("id") + "'";
        final String tableName = request.getParameter("tableName");
        // System.out.println(tableName);
        // System.out.println(id);

        logger.info("Select from table: " + tableName + " where id = " + id);
        try {
            final Student result = service.selectOneFrom(tableName, id);
            System.out.println(result);

            return result.toString();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/insert", method = { RequestMethod.POST })
    @ResponseBody
    public void insert(final HttpServletRequest request) {
        final String idString = request.getParameter("id");
        final int id = Integer.valueOf(idString);
        final String givenName = request.getParameter("givenname");
        final String surname = request.getParameter("surname");
        final String university = request.getParameter("university");

        logger.info("Insert into table: " + id + " " + givenName + " " + surname + " " + university);
        try {
            service.insert(id, surname, givenName, university);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    @ResponseBody
    public void delete(final HttpServletRequest request) {
        final String id = request.getParameter("id");

        logger.info("Delete from table: " + id);
        try {
            service.delete(id);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }
}
