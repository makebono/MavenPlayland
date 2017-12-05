/**Note:
 * Was not quite sure about reflection. So here is a little test.
 * Simple flow:
 *     c = getClass -> m = getMethod -> m.invoke(s) to call the method. 
 */
package com.makebono.mavenplayland.test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.makebono.mavenplayland.module_test.common.connector.SqlConnector;
import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: SimpleReflectionTest 
 * @Description: Test class 
 * @author makebono
 * @date 2017年11月29日 下午3:54:47 
 *  
 */
public class SimpleReflectionTest {
    public static void main(final String[] args) throws IOException, NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Student s = SqlConnector.selectById("1");
        final Class<?> c = s.getClass();
        try {
            final Method m = c.getMethod("getGivenName");
            System.out.println(m.invoke(s));
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
        
        
    }
}
