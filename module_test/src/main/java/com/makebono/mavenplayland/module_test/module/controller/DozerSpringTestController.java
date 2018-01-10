package com.makebono.mavenplayland.module_test.module.controller;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.entities.BCResident;
import com.makebono.mavenplayland.module_test.module.entities.CoquitlamResident;
import com.makebono.mavenplayland.module_test.module.entities.Resident;

/** 
 * @ClassName: DozerSpringTestController 
 * @Description: Test web based dozer mapping.
 * @author makebono
 * @date 2018年1月10日 上午10:55:11 
 *  
 */
@Controller
@RequestMapping("/dozerTest")
public class DozerSpringTestController {
    private static final Logger logger = LoggerFactory.getLogger(DozerSpringTestController.class);
    private static Resident ass;

    // After configured in application context file, don't forget to autowire a mapper in controller. Or it will use
    // default mapper instead.
    @Autowired
    private DozerBeanMapper mapper;

    static {
        ass = new Resident();
        ass.setAge("128");
        ass.setId("1234567");
        ass.setName("サノバビッチ");
    }

    // @PostConstruct forces this method to be executed after DI.
    @PostConstruct
    public void init() {
        this.mapper.map(ass, Object.class);
    }

    @RequestMapping(value = "/directMapping")
    @ResponseBody
    public BCResident directMapping() {
        logger.info("Dozer direct mapping.");
        try {
            final BCResident assBc = this.mapper.map(ass, BCResident.class);
            System.out.println(assBc);
            return assBc;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/xmlConfiguredMapping")
    @ResponseBody
    public CoquitlamResident xmlMapping() {
        logger.info("Dozer xml configured mapping.");
        try {
            final CoquitlamResident assCoq = this.mapper.map(ass, CoquitlamResident.class);
            System.out.println(assCoq);
            return assCoq;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
