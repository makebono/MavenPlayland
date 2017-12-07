package com.makebono.mavenplayland.test.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/** 
 * @ClassName: AbstractLogIn 
 * @Description: For logging in.
 * @author com.github.zhangkaitao
 * @date 2017年12月7日 上午8:49:01 
 *  
 */
public abstract class AbstractLogIn {
    protected static Subject login(final String config, final String userName, final String password) {
        final Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(config);

        final org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        final Subject subject = SecurityUtils.getSubject();
        final UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        try {
            subject.login(token);
            return subject;
        }
        catch (final AuthenticationException e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
