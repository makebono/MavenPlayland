package com.makebono.mavenplayland.module_test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.entities.Student;
import com.makebono.mavenplayland.module_test.service.QueryService;

/** 
 * @ClassName: DBQueryController 
 * @Description: A little database calling controller
 * @author makebono
 * @date 2017年12月1日 上午9:07:47 
 *  
 */
@Controller
@RequestMapping("/sqlTest")
public class DBQueryController {
    private static final Logger logger = LoggerFactory.getLogger(DBQueryController.class);

    @Autowired
    private QueryService sqlService;

    @RequestMapping(value = "/queryById", method = { RequestMethod.POST })
    @ResponseBody
    public String queryById(final HttpServletRequest request) {
        final String id = request.getParameter("id");

        // System.out.println(id);

        logger.info("Query by id: ", id);
        try {
            final Student result = sqlService.selectById(id);
            System.out.println(result);

            return result.toString();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public String selectAll() {
        logger.info("Select all from maven_test ");
        try {
            final List<Student> result = sqlService.selectAll();
            final StringBuilder sb = new StringBuilder();
            for (final Student cursor : result) {
                sb.append(cursor + "<br>");
            }
            return sb.toString();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/selectOneFrom", method = { RequestMethod.POST })
    @ResponseBody
    public String selectOneFrom(final HttpServletRequest request) {
        final String id = "'" + request.getParameter("id") + "'";
        final String tableName = request.getParameter("tableName");
        System.out.println(tableName);
        System.out.println(id);
        // System.out.println(id);

        logger.info("Select from table by id: ", tableName, id);
        try {
            final Student result = sqlService.selectOneFrom(tableName, id);
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

        logger.info("Insert to table: ", id, givenName, surname, university);
        try {
            sqlService.insert(id, surname, givenName, university);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.POST })
    @ResponseBody
    public void delete(final HttpServletRequest request) {
        final String idString = request.getParameter("id");
        final int id = Integer.valueOf(idString);

        logger.info("Delete from table: ", id);
        try {
            sqlService.delete(id);
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }
}
