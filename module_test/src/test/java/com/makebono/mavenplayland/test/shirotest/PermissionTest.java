/**Note:
 * In configurations, permission A has properties below:
 *    1. A:* can match every permission starts as "A:", for example A:delete.
 *    2. A:*:delete can match permissions like A:account:delete
 *    3. A:* cound be shorten as A    
 *    4. Multiple permission could be listed in a single column in database or in a row in .ini file. i.e.:  
 *        role_name: makebono    permission: A:delete,B:delete
 *        role_name: makebono    permission: A:delete,update,create 
 */
package com.makebono.mavenplayland.test.shirotest;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;

import com.makebono.mavenplayland.test.shirotest.service.RealmLogin;

/** 
 * @ClassName: PermissionTest 
 * @Description: PermissionTest 
 * @author makebono
 * @date 2018年2月8日 上午10:40:34 
 *  
 */
public class PermissionTest {
    public static void testIni(final String... input) {
        Subject subject;
        boolean permitted = false;
        try {
            subject = (Subject) RealmLogin.login("src/test/resources/shiro/shiro-role.ini", input[0], input[1]);
            permitted = subject.isPermittedAll(Arrays.copyOfRange(input, 2, input.length));
        }
        catch (final Throwable e) {

        }
        finally {
            String result = "";
            result = permitted ? ("User has input permissions.") : ("User doesn't have input permissions.");
            System.out.println(result);
        }
    }

    public static void testDb(final String... input) {
        Subject subject;
        boolean permitted = false;
        try {
            subject = (Subject) RealmLogin.login("src/test/resources/shiro/shiro-jbdc-realm.ini", input[0], input[1]);
            permitted = subject.isPermittedAll(Arrays.copyOfRange(input, 2, input.length));

        }
        catch (final Throwable e) {

        }
        finally {
            String result = "";
            result = permitted ? ("User has input permissions.") : ("User doesn't have input permissions.");
            System.out.println(result);
        }
    }

    public static void main(final String[] args) {
        testDb("fredfuchs", "000", "account:update");
    }
}
