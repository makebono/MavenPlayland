package com.makebono.mavenplayland.test.shirotest.service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.makebono.mavenplayland.module_test.module.entities.UserAccount;

/** 
 * @ClassName: UserAccountConnector 
 * @Description: UserAccountConnector 
 * @author makebono
 * @date 2018年2月7日 下午3:28:20 
 *  
 */
public class UserAccountConnector {

    private static Reader reader;
    private static SqlSessionFactory factory;
    static SqlSession session;
    static UserAccountService mapper;

    static {
        try {
            reader = Resources
                    .getResourceAsReader("com/makebono/mavenplayland/test/testconfigurations/testSqlConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
            session = factory.openSession();
            mapper = session.getMapper(UserAccountService.class);

        }
        catch (final IOException e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }

    }

    public static List<UserAccount> selectAll() {
        List<UserAccount> result;

        try {
            result = mapper.selectAll();

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
