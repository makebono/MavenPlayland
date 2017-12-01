package com.makebono.mavenplayland.module_test.connector;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.makebono.mavenplayland.module_test.entities.Student;

/** 
 * @ClassName: SqlConnector
 * @Description: MyBatis sql connector
 * @author makebono
 * @date 2017年11月29日 下午3:07:33 
 *  
 */
public class SqlConnector {
    private final static Logger logger = LoggerFactory.getLogger(SqlConnector.class);
    private static Reader reader;
    private static SqlSessionFactory factory;
    static SqlSession session;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);;
            session = factory.openSession();
        }
        catch (final IOException e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }

    }

    public static Student selectById(final int id) {
        final Student result;

        logger.info("Select * from maven_test where ID = 'id'", id);

        try {
            result = session.selectOne("com.makebono.mavenplayland.module_test.connector.selectById", id);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }
}
