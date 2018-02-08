package com.makebono.mavenplayland.module_test.module.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.common.system.security.AuthenticationService;
import com.makebono.mavenplayland.module_test.common.system.security.UserLoginInfo;

/** 
 * @ClassName: LoginController 
 * @Description: LoginController 
 * @author makebono
 * @date 2018年2月8日 下午4:37:49 
 *  
 */
@Controller
public class LoginController {

    @Autowired
    private AuthenticationService service;

    @RequestMapping("/login")
    @ResponseBody
    public String login(final HttpServletRequest request) {
        if (UserLoginInfo.getSubject() != null) {
            return "Already logged in.";
        }

        final String username = request.getParameter("username");
        final String password = request.getParameter("password");

        try {
            final Subject subject = (Subject) this.service.login(username, password);
            UserLoginInfo.setSubject(subject);
            return "Login successed.";
        }
        catch (final Exception e) {
            return "Access denied, message: " + e.getMessage();
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout() {
        final Subject subject = UserLoginInfo.getSubject();
        if (subject == null) {
            return "Not yet logged in.";
        } else {
            subject.logout();
            UserLoginInfo.setSubject(null);
            return "Logged out successed.";
        }
    }
}
