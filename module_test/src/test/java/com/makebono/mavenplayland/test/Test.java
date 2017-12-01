package com.makebono.mavenplayland.test;

import java.io.IOException;

import com.makebono.mavenplayland.module_test.connector.SqlConnector;

/** 
 * @ClassName: Test 
 * @Description: Test class 
 * @author makebono
 * @date 2017年11月29日 下午3:54:47 
 *  
 */
public class Test {
    public static void main(final String[] args) throws IOException {
        System.out.println(SqlConnector.selectById(1));
    }
}
