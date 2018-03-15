package com.makebono.mavenplayland.module_web.module.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.module.service.MailSendingService;

/** 
 * @ClassName: MailSendingController 
 * @Description: Test sending e-mails.
 * @author makebono
 * @date 2018年1月19日 下午5:21:26 
 *  
 */
@Controller
@RequestMapping("/mailTest")
public class MailSendingController {
    private static final Logger logger = LoggerFactory.getLogger(MailSendingController.class);

    @Autowired
    private MailSendingService service;

    @RequestMapping(value = "/send", method = { RequestMethod.POST })
    @ResponseBody
    public String sendMail(final HttpServletRequest request, final HttpServletResponse response) {
        // Do not forget 'from' parameter. At least for hotmail, this is mandatory.
        final String from = "xxxxxx@xxxxxx.com";
        final String to = request.getParameter("to");
        final String subject = request.getParameter("subject");
        final String body = request.getParameter("body");

        logger.info("Sending email.\nFrom: " + from + "\nTo: " + to + "\nSubject: " + subject + "\nBody: " + body);
        try {
            service.sendMail(from, to, subject, body);
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }

        return "E-mail sended, check it out";
    }

}
