package com.makebono.mavenplayland.test.shirotest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.makebono.mavenplayland.test.shirotest.service.RealmLogin;

/** 
 * @ClassName: ShiroRoleTest 
 * @Description: Test on roles 
 * @author makebono
 * @date 2017年12月7日 上午8:44:01 
 *  
 */
public class ShiroRoleTest extends AbstractLogIn {

    private static Object dbLogin(final String... user) {
        final String configFile = "src/test/resources/shiro/shiro-jbdc-realm.ini";

        try {
            final Subject subject = (Subject) RealmLogin.login(configFile, user[0], user[1]);
            return subject;
        }
        catch (final Throwable e) {
            System.out.println("Credential failed.");
            throw new IncorrectCredentialsException("Credential failed.");
        }

    }

    @Test
    public static void hasRoleFromIni(final String... roles) {
        boolean hasRole = false;
        try {
            final Subject subject = login("src/test/resources/shiro/shiro-role.ini", "marston", "123");
            final List<String> rolesRequired = new LinkedList<String>();
            rolesRequired.addAll(Arrays.asList(roles));
            // hasRole for single role checking, hasRoles returnes a boolean array for validities of each roles.
            hasRole = subject.hasAllRoles(rolesRequired);
            Assert.assertTrue(hasRole);
        }
        catch (final Throwable e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
        finally {
            String result = "";
            result = hasRole ? ("Candidate has required role authorization.") : ("Candidate is not authorized.");
            System.out.println(result);
        }
    }

    @Test
    public static void hasRoleFromDB(final String... input) {
        boolean hasRole = false;

        try {
            final Subject subject = (Subject) dbLogin(input[0], input[1]);
            final List<String> rolesRequired = new LinkedList<String>();
            rolesRequired.addAll(Arrays.asList(Arrays.copyOfRange(input, 2, input.length)));
            hasRole = subject.hasAllRoles(rolesRequired);
            Assert.assertTrue(hasRole);
        }
        catch (final Throwable e) {
            System.out.println("Error occurs, message: Candidate is not authorized with input roles.");
        }
        finally {
            String result = "";
            result = hasRole ? ("Candidate has required role authorization.") : ("Candidate is not authorized.");
            System.out.println(result);
        }
    }

    public static void main(final String[] args) {
        hasRoleFromDB("marston", "123", "user", "tester");
    }
}
