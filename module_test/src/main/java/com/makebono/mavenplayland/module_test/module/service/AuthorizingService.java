package com.makebono.mavenplayland.module_test.module.service;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.system.security.UserLoginInfo;

/** 
 * @ClassName: AuthorizingService 
 * @Description: AuthorizingService 
 * @author makebono
 * @date 2018年2月8日 下午5:00:05 
 *  
 */
@Service
public class AuthorizingService {
    public boolean inspect(final String permission) {
        final Subject subject = UserLoginInfo.getSubject();

        if (subject == null) {
            throw new CredentialsException("Login is required.");
        }

        return subject.isPermitted(permission);
    }
}
