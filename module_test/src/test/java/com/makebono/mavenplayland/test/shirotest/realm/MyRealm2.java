package com.makebono.mavenplayland.test.shirotest.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/** 
 * @ClassName: MyRealm2 
 * @Description: MyRealm2
 * @author com.github.zhangkaitao
 * @date 2017年12月5日 上午9:57:59 
 *  
 */
public class MyRealm2 implements Realm {

    @Override
    public String getName() {
        return "myrealm2";
    }

    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

        final String username = (String) token.getPrincipal();
        final String password = new String((char[]) token.getCredentials());

        // Account and password different from realm1 and 3. So it won't pass authentication if been parsed to
        // securityManager.realms along with realm1 and/or realm3.
        if (!"bjj".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
