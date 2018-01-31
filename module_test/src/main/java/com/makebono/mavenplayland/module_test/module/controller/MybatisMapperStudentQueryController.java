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
import com.makebono.mavenplayland.module_test.module.service.MybatisMapperStudentQueryService;

/** 
 * @ClassName: MybatisMapperStudentQueryController 
 * @Description: Sql querying by Mybatis mapper
 * @author makebono
 * @date 2018年1月31日 下午3:30:08 
 *  
 */
@Controller
@RequestMapping("/mybatis")
public class MybatisMapperStudentQueryController {
    private static final Logger logger = LoggerFactory.getLogger(MybatisMapperStudentQueryController.class);
    @Autowired
    private MybatisMapperStudentQueryService service;

    @RequestMapping(value = "/selectOne", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Student selectOne(final HttpServletRequest request) {
        // Type convert problem here, get it done.
        final long key = Long.valueOf(request.getParameter("ID"));
        logger.info("Select one from maven_test with key(" + key + ")");
        try {
            final Student result = this.service.selectByKey(key);

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/selectAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<Student> selectAll() {
        logger.info("Select all from maven_test ");
        try {
            final List<Student> result = this.service.selectAll();

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String insert(final HttpServletRequest request) {
        final String idString = request.getParameter("ID");
        final long id = Long.valueOf(idString);
        final String givenName = request.getParameter("GIVENNAME");
        final String surname = request.getParameter("SURNNAME");
        final String university = request.getParameter("UNIVERSITY");
        final Student candidate = new Student();
        candidate.setGivenName(givenName);
        candidate.setId(id);
        candidate.setUniversity(university);
        candidate.setSurname(surname);

        logger.info("Insert into table: " + id + " " + givenName + " " + surname + " " + university);
        try {
            this.service.save(candidate);
            return candidate + "\nInsert success";
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String delete(final HttpServletRequest request) {
        final String key = request.getParameter("id");

        logger.info("Delete from table: " + key);
        try {
            this.service.delete(key);
            return "Entity with id(" + key + ") removed from table";
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }
}
