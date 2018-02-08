package com.makebono.mavenplayland.test.shirotest.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/** 
 * @ClassName: RealmLogin 
 * @Description: RealmLogin 
 * @author makebono
 * @date 2018年2月8日 上午10:41:12 
 *  
 */
public class RealmLogin {
    public static Object login(final String configFile, final String username, final String password) {

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
        catch (final Throwable e) {
            System.out.println("Credential failed.");
            throw new IncorrectCredentialsException("Credential failed.");
        }

    }
}
