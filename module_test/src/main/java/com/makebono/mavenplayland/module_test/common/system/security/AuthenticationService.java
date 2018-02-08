package com.makebono.mavenplayland.module_test.common.system.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: AuthenticationService 
 * @Description: AuthenticationService 
 * @author makebono
 * @date 2018年2月8日 下午2:34:17 
 *  
 */
@Service
public class AuthenticationService {
    public Object login(final String username, final String password) {
        final String configFile = "d:/workspace/MavenPlayland/module_test/src/main/resources/shiro/shiro-authenticator-db.ini";
        // Initilize SecurityManager factory
        final Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

        // Get SecurityManager
        final SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Authenticate given username and password.
        final Subject subject = SecurityUtils.getSubject();
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            return subject;
        }
        catch (final UnknownAccountException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        catch (final IncorrectCredentialsException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
