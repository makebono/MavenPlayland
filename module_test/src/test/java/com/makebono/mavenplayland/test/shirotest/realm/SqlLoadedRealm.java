package com.makebono.mavenplayland.test.shirotest.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

import com.makebono.mavenplayland.module_test.module.entities.UserAccount;
import com.makebono.mavenplayland.test.shirotest.service.UserAccountConnector;

/** 
 * @ClassName: SqlLoadedRealm 
 * @Description: SqlLoadedRealm 
 * @author makebono
 * @date 2018年2月7日 下午3:50:36 
 *  
 */
public class SqlLoadedRealm implements Realm {

    private static List<UserAccount> info;

    static {
        info = UserAccountConnector.selectAll();
        System.out.println("Security info loaded from database.");
    }

    @Override
    public String getName() {
        return "sqllodedrealm";
    }

    // Define supported token type.
    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {

        final String username = (String) token.getPrincipal();
        final String password = new String((char[]) token.getCredentials());
        final Object candidate = findUser(username);
        if (candidate.equals(Boolean.FALSE)) {
            throw new UnknownAccountException();
        }
        if (!((UserAccount) candidate).getPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    public static Object findUser(final String account) {
        for (final UserAccount cursor : info) {
            if (account.equals(cursor.getUsername())) {
                return cursor;
            }
        }
        return Boolean.FALSE;
    }
}
