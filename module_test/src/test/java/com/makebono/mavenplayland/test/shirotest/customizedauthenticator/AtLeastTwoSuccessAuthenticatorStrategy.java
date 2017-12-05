package com.makebono.mavenplayland.test.shirotest.customizedauthenticator;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.CollectionUtils;

/** 
 * @ClassName: AtLeastTwoSuccessAuthenticatorStrategy 
 * @Description: AtLeastTwoSuccessAuthenticatorStrategy
 * @author com.github.zhangkaitao
 * @date 2017年12月5日 上午10:37:47 
 *  
 */
public class AtLeastTwoSuccessAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    // Before validating all realms.
    @Override
    public AuthenticationInfo beforeAllAttempts(final Collection<? extends Realm> realms,
            final AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo();// 返回一个权限的认证信息
    }

    // Before validating each realm. Notice the different from before all...
    @Override
    public AuthenticationInfo beforeAttempt(final Realm realm, final AuthenticationToken token,
            final AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;// 返回之前合并的
    }

    // After each validation.
    @Override
    public AuthenticationInfo afterAttempt(final Realm realm, final AuthenticationToken token,
            final AuthenticationInfo singleRealmInfo, final AuthenticationInfo aggregateInfo, final Throwable t)
            throws AuthenticationException {
        AuthenticationInfo info;
        if (singleRealmInfo == null) {
            info = aggregateInfo;
        } else {
            if (aggregateInfo == null) {
                info = singleRealmInfo;
            } else {
                info = merge(singleRealmInfo, aggregateInfo);
            }
        }

        return info;
    }

    // After all validations.
    // So from here it's easy to see that this authenticator is counting number of principals(successfully authenticated
    // user names) for the restriction of "at least two success."
    @Override
    public AuthenticationInfo afterAllAttempts(final AuthenticationToken token, final AuthenticationInfo aggregate)
            throws AuthenticationException {
        if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals())
                || aggregate.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] "
                    + "could not be authenticated by any configured realms.  Please ensure that at least two realm can "
                    + "authenticate these tokens.");
        }

        return aggregate;
    }
}
