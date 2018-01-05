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

import com.makebono.mavenplayland.module_test.mapper.MappingInterface;
import com.makebono.mavenplayland.module_test.module.entities.MicroArchitecture;
import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: InterfaceSqlConnector 
 * @Description: Sql connector class for interface query.
 * @author makebono
 * @date 2017年12月4日 上午9:37:32 
 *  
 */
public class InterfaceSqlConnector {
    private final static Logger logger = LoggerFactory.getLogger(SqlConnector.class);
    private static Reader reader;
    private static SqlSessionFactory factory;
    static SqlSession session;
    static MappingInterface mapper;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);;
            session = factory.openSession();
            mapper = session.getMapper(MappingInterface.class);
        }
        catch (final IOException e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }

    }

    public static Student selectById(final String id) {
        final Student result;

        logger.info("Select * from maven_test where ID = 'id'", id);

        try {
            result = mapper.selectOne(id);
            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static MicroArchitecture selectMicroArchitecture(final String MODEL) {
        final MicroArchitecture result;

        logger.info("Select * from maven_test2 where MODEL = 'MODEL'", MODEL);

        try {
            result = mapper.selectOneMicroArchitecture(MODEL);
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
            result = mapper.selectAll();

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static List<MicroArchitecture> selectAllMicroArchitecture() {
        List<MicroArchitecture> result;

        logger.info("Select * from maven_test2");

        try {
            result = mapper.selectAllMicroArchitecture();

            return result;
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
            return null;
        }
    }

    public static Student selectOneFrom(final Map<String, Object> query) {
        final Student result;

        logger.info("Select * from {tableName} where ID = 'id'", query);

        try {
            result = mapper.selectOneFrom(query);
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
        logger.info("Adding student into database." + newStudent);

        try {
            mapper.insert(newStudent);
            session.commit();
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }

    public static void delete(final String id) {
        logger.info("Remove student from database by Id", id);
        try {
            mapper.delete(id);
            session.commit();
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }
    }
}
