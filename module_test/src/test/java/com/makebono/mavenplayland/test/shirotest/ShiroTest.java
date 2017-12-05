/**Note:
 * Simple Shiro authentication test, basically copied and pasted from:
 * https://github.com/tencent-rep/zhangkaitao-shiro-example/blob/master/shiro-example-chapter2/src/test/java/com/github/zhangkaitao/shiro/chapter2/LoginLogoutTest.java
 * With some tiny little modifies.
 */
package com.makebono.mavenplayland.test.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

/** 
 * @ClassName: ShiroTest 
 * @Description: ShiroTest
 * @author com.github.zhangkaitao
 * @date 2017年12月4日 下午4:21:53 
 *  
 */
public class ShiroTest {
    @Test
    public static void test() {
        // Account book contains account informations.
        final Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
                "src/test/resources/shiro/shiro.ini");

        final org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        final Subject subject = SecurityUtils.getSubject();
        final UsernamePasswordToken token = new UsernamePasswordToken("marston", "006");

        try {
            subject.login(token);
        }
        // Actually it's an AuthenticationException
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }

        // A good timing for me to recall memory about Assert, nearly forgot about it. If assertion failed then error
        // would be thrown and following lines won't be executed.
        Assert.assertEquals(true, subject.isAuthenticated());
        System.out.println("Signed in!");
        subject.logout();
    }

    public static void main(final String[] args) {
        test();
    }
}
