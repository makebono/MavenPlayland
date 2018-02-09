package com.makebono.mavenplayland.module_test.common.system.security.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.makebono.mavenplayland.module_test.common.system.security.SecurityInfoCache;
import com.makebono.mavenplayland.module_test.module.entities.UserAccount;

/** 
 * @ClassName: AuthenticationRealm 
 * @Description: AuthenticationRealm 
 * @author makebono
 * @date 2018年2月8日 下午3:24:39 
 *  
 */
@SuppressWarnings("unchecked")
public class AuthenticationRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return AuthenticationRealm.class.getSimpleName();
    }

    @Override
    public boolean supports(final AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        final String username = (String) getAvailablePrincipal(principals);
        Set<String> roleNames = null;
        Set<String> permissions = null;

        roleNames = (Set<String>) SecurityInfoCache.findRolesForUser(username);
        System.out.println(roleNames);
        for (final String role : roleNames) {
            if (permissions == null) {
                permissions = (Set<String>) SecurityInfoCache.findPermissionsForRole(role);
            } else {
                permissions.addAll((Set<String>) SecurityInfoCache.findPermissionsForRole(role));
            }
        }

        final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        System.out.println(permissions);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token)
            throws AuthenticationException {
        final String username = (String) token.getPrincipal();
        final String password = new String((char[]) token.getCredentials());

        final Object candidate = SecurityInfoCache.findUser(username);

        if (candidate.equals(Boolean.FALSE)) {
            // System.out.println("cannot find user");
            throw new UnknownAccountException("Invalid account.");
        }

        // System.out.println("user found");

        if (!((UserAccount) candidate).getPassword().equals(password)) {
            throw new IncorrectCredentialsException("Invalid password.");
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
