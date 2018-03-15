package com.makebono.mavenplayland.module_web.module.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.MicroArchitecture;
import com.makebono.mavenplayland.module_test.module.service.MybatisMapperMicroArchitectureQueryService;

/** 
 * @ClassName: MybatisMapperMicroArchitectureQueryController 
 * @Description: MybatisMapperMicroArchitectureQueryController 
 * @author makebono
 * @date 2018年1月31日 下午5:47:54 
 *  
 */
@Controller
@RequestMapping("/mybatisma")
public class MybatisMapperMicroArchitectureQueryController {
    private static final Logger logger = LoggerFactory.getLogger(MybatisMapperStudentQueryController.class);
    @Autowired
    private MybatisMapperMicroArchitectureQueryService service;

    @RequestMapping(value = "/selectOne", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public MicroArchitecture selectOne(final HttpServletRequest request) {
        final String key = request.getParameter("MODEL");
        logger.info("Select one from maven_test with key(" + key + ")");
        try {
            final MicroArchitecture result = this.service.selectByKey(key);
            logger.info("Query complete, result is:\n    " + result);
            return result;
        }
        catch (final Exception e) {
            logger.info("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/selectAll", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<MicroArchitecture> selectAll() {
        logger.info("Select all from maven_test ");
        try {
            final List<MicroArchitecture> result = this.service.selectAll();

            for (final MicroArchitecture candidate : result) {
                logger.info(candidate.toString());
            }

            return result;
        }
        catch (final Exception e) {
            logger.info("Error occurs, message: " + e.getMessage());
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/insert", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String insert(final HttpServletRequest request) {
        final String brand = request.getParameter("BRAND");
        final String core = request.getParameter("CORE");
        final String model = request.getParameter("MODEL");
        final MicroArchitecture candidate = new MicroArchitecture();
        candidate.setBrand(brand);
        candidate.setCore(core);
        candidate.setModel(model);

        logger.info("Insert into table: " + brand + " " + core + " " + model);
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

    @RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String delete(final HttpServletRequest request) {
        final String key = request.getParameter("MODEL");

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
}
