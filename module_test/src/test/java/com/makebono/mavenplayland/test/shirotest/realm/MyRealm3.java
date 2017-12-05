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
 * @ClassName: MyRealm3 
 * @Description: MyRealm3
 * @author com.github.zhangkaitao
 * @date 2017年12月5日 上午9:58:08 
 *  
 */
public class MyRealm3 implements Realm {

    @Override
    public String getName() {
        return "myrealm3";
    }

    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

        final String username = (String) token.getPrincipal();
        final String password = new String((char[]) token.getCredentials());
        if (!"marston".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"006".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        // Different return text.
        return new SimpleAuthenticationInfo(username + "@163.com", password, getName());
    }
}