package com.makebono.mavenplayland.module_test.common.system.security;

import org.apache.shiro.subject.Subject;

/** 
 * @ClassName: UserLoginInfo 
 * @Description: Initialized by login controller. Holding account, role, permission of user. Just for fun and test.
 * @author makebono
 * @date 2018年2月8日 下午4:32:54 
 *  
 */
public class UserLoginInfo {
    private static Subject subject;

    private UserLoginInfo() {

    }

    public static void setSubject(final Subject newSubject) {
        subject = newSubject;
    }

    public static Subject getSubject() {
        return subject;
    }
}
