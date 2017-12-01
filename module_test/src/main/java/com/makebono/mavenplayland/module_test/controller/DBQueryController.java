package com.makebono.mavenplayland.module_test.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Try get the @Autowired working.
    // @Autowired
    private final QueryService sqlService = new QueryService();

    @RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String queryById(final HttpServletRequest request) {
        final String idString = request.getParameter("id");
        final int id = Integer.valueOf(idString);

        // System.out.println(id);

        logger.info("Query by id: ", id);
        try {
            final Student result = sqlService.selectById(id);
            return result.toString();
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }
    }

}
