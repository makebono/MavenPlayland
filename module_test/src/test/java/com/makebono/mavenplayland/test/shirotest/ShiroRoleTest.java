package com.makebono.mavenplayland.test.shirotest;

import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/** 
 * @ClassName: ShiroRoleTest 
 * @Description: Test on roles 
 * @author com.github.zhangkaitao
 * @date 2017年12月7日 上午8:44:01 
 *  
 */
public class ShiroRoleTest extends AbstractLogIn {
    @Test
    public void hasRole() {
        final Subject subject = login("src/test/resources/shiro/shiro-role.ini", "marston", "006");
        Assert.assertTrue(subject.hasRole("admin"));
    }
}
