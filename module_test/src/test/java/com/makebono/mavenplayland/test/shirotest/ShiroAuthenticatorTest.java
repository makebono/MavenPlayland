package com.makebono.mavenplayland.test.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.makebono.mavenplayland.test.shirotest.service.RealmLogin;

import junit.framework.Assert;

/** 
 * @ClassName: AuthenticatorTest 
 * @Description: AuthenticatorTest
 * @author com.github.zhangkaitao
 * @date 2017年12月5日 上午10:08:29 
 *  
 */
public class ShiroAuthenticatorTest {
    private void login(final String configFile) {
        RealmLogin.login(configFile, "fredfuchs", "000");
    }

    @Test
    public void testAllSuccessfulStrategy_Success() {
        login("src/test/resources/shiro/shiro-authenticator-all-success.ini");
        final Subject subject = SecurityUtils.getSubject();
        final PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println("Signed as: ");
        if (principalCollection.getPrimaryPrincipal().equals(principalCollection.asList().get(0))) {
            System.out.println(principalCollection.asList().get(0));
        }
        System.out.println(principalCollection.asList().get(1));

    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategy_Fail() {
        login("src/test/resources/shiro/shiro-authenticator-all-fail.ini");
    }

    @Test
    public void testAtLeastOneSuccessfulStrategy_Success() {
        login("src/test/resources/shiro/shiro-authenticator-atLeastOne-success.ini");

        final Subject subject = SecurityUtils.getSubject();
        final PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println("Signed as: ");
        if (principalCollection.getPrimaryPrincipal().equals(principalCollection.asList().get(0))) {
            System.out.println(principalCollection.asList().get(0));
        }
        System.out.println(principalCollection.asList().get(1));
    }

    @Test
    public void testAtLeastTwoSuccessfulStrategy_Success() {
        login("src/test/resources/shiro/shiro-authenticator-atLeastTwo-success.ini");

        // A subject is a user.
        final Subject subject = SecurityUtils.getSubject();
        final PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
        System.out.println("Signed as: ");
        if (principalCollection.getPrimaryPrincipal().equals(principalCollection.asList().get(0))) {
            System.out.println(principalCollection.asList().get(0));
        }
        System.out.println(principalCollection.asList().get(1));
    }

    @Test(expected = UnknownAccountException.class)
    public void testAtLeastTwoSuccessfulStrategy_Fail() {
        login("src/test/resources/shiro/shiro-authenticator-atLeastTwo-fail.ini");
    }

    // Self-written jdbc realm
    @Test
    public void testDBRealm() {
        login("src/test/resources/shiro/shiro-authenticator-db.ini");
    }

    // Shiro built-in jdbc realm. Table and column name have strict restrictions.
    @Test
    public void testJDBCRealm() {
        login("src/test/resources/shiro/shiro-jbdc-realm.ini");
    }

    public static void main(final String[] args) {
        final ShiroAuthenticatorTest t = new ShiroAuthenticatorTest();
        t.testDBRealm();
    }
}
