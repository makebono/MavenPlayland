/**Note:
 * Follows RESTful APIs properties. To sum it up, every resouce has a dedicated URI (as for here, /students), and use different request
 * methods for different operations. GET for selecting, DELETE for removing, PUT for modifying and POST for inserting.
 * POST and PUT need to be idempotent, GET requires to be safe (i.e. no effects on data) and POST doesn't have these restrictions.
 * Remember PUT and DELETE method are not defaultly enabled by Tomcat server. Need to modify:
 * config in server.xml:
 *     
 * <Connector connectionTimeout="xxxx" port="xxxx" protocol="HTTP/1.1" redirectPort="xxxx" parseBodyMethods="POST,PUT,DELETE,GET"/>
 *  
 * Others are nothing special. 
 */
package com.makebono.mavenplayland.module_web.module.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.Student;
import com.makebono.mavenplayland.module_test.module.service.MybatisMapperStudentQueryService;

/** 
 * @ClassName: RESTStudentController 
 * @Description: Adhere to REST architecture
 * @author makebono
 * @date 2018年2月5日 下午2:09:30 
 *  
 */
@Controller
@RequestMapping("/students")
public class RESTStudentController {
    private static final Logger logger = LoggerFactory.getLogger(RESTStudentController.class);

    @Autowired
    private MybatisMapperStudentQueryService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Student> queryById(@RequestParam(required = false, value = "id") final String id) {

        try {
            if (id == null) {
                logger.info("Select all from maven_test");
                return service.selectAll();
            }
            logger.info("Query by id: " + id);
            final List<Student> result = new ArrayList<Student>();
            final long numericId = Long.valueOf(id);
            result.add(service.selectByKey(numericId));
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String insert(final HttpServletRequest request) {
        final String idString = request.getParameter("id");
        final long id = Long.valueOf(idString);
        final String givenName = request.getParameter("givenname");
        final String surname = request.getParameter("surnname");
        final String university = request.getParameter("university");
        final Student candidate = new Student();
        candidate.setGivenName(givenName);
        candidate.setId(id);
        candidate.setUniversity(university);
        candidate.setSurname(surname);

        logger.info("Insert into table: " + id + " " + givenName + " " + surname + " " + university);
        try {
            this.service.save(candidate);
            logger.info(candidate + "\nInsert success");
            return candidate + "\nInsert success";
        }
        catch (final Exception e) {
            logger.info("Error occurs, message: " + e.getMessage());
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(final HttpServletRequest request) {
        final long key = Long.valueOf(request.getParameter("id"));

        logger.info("Delete from table: " + key);
        try {
            this.service.delete(key);
            logger.info("Entity with id(" + key + ") removed from table");
            return "Entity with id(" + key + ") removed from table";
        }
        catch (final Exception e) {
            logger.info("Error occurs, message: " + e.getMessage());
            return "Error occurs, message: " + e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String modify(@RequestParam(required = true, value = "student") final Student student,
            final HttpServletRequest request) {

        final long id = student.getId();

        try {
            if (service.select(student).size() == 0) {
                service.save(student);
                return "Record not found, input entry added to database:\n    " + student.toString();
            }

            service.delete(id);
            service.save(student);
            return "Database updated:\n    " + student.toString();

        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }
}
