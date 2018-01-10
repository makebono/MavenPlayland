package com.makebono.mavenplayland.module_test.common.connector;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.makebono.mavenplayland.module_test.module.entities.Student;

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

    public static Student selectById(final String id) {
        final Student result;

        logger.info("Select * from maven_test where ID = 'id' " + id);

        try {
            result = session.selectOne("com.makebono.mavenplayland.module_test.module.connector.selectById", id);

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static List<Student> selectAll() {
        List<Student> result;

        logger.info("Select * from maven_test");

        try {
            result = session.selectList("com.makebono.mavenplayland.module_test.module.connector.selectAll");

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static Student selectOneFrom(final Map<String, Object> query) {
        final Student result;

        logger.info("Select * from {tableName} where ID = 'id' " + query);

        try {
            result = session.selectOne("com.makebono.mavenplayland.module_test.module.connector.selectFromTable",
                    query);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static void addStudent(final int id, final String surname, final String givenName, final String university) {
        final Student newStudent = new Student();
        newStudent.setGivenName(givenName);
        newStudent.setId(id);
        newStudent.setSurname(surname);
        newStudent.setUniversity(university);
        logger.info("Adding student into database " + newStudent);

        try {
            session.insert("com.makebono.mavenplayland.module_test.module.connector.insert", newStudent);
            session.commit();
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }

    public static void delete(final String id) {
        logger.info("Remove student from database by Id " + id);
        try {
            session.delete("com.makebono.mavenplayland.module_test.module.connector.delete", id);
            session.commit();
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }
}
