/**Note:
 * Simple Shiro authentication test, basically copied and pasted from:
 * https://github.com/tencent-rep/zhangkaitao-shiro-example/blob/master/shiro-example-chapter2/src/test/java/com/github/zhangkaitao/shiro/chapter2/LoginLogoutTest.java
 * With some tiny little modifications.
 */
package com.makebono.mavenplayland.test.shirotest;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

import junit.framework.Assert;

/** 
 * @ClassName: ShiroTest 
 * @Description: ShiroTest
 * @author com.github.zhangkaitao
 * @date 2017年12月4日 下午4:21:53 
 *  
 */
public class ShiroTest extends AbstractLogIn {
    @Test
    public static void test() {
        final Subject subject = login("src/test/resources/shiro/shiro.ini", "marston", "006");

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
