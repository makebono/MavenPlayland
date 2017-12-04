package com.makebono.mavenplayland.module_test.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.connector.InterfaceSqlConnector;
import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: InterfaceQueryService 
 * @Description: Service class for interface query
 * @author makebono
 * @date 2017年12月4日 上午9:35:45 
 *  
 */
@Service
public class InterfaceQueryService {
    public Student selectById(final String id) {
        return InterfaceSqlConnector.selectById(id);
    }

    public List<Student> selectAll() {
        return InterfaceSqlConnector.selectAll();
    }

    public Student selectOneFrom(final String tableName, final String id) {
        final Map<String, Object> query = new HashMap<String, Object>();
        query.put("tableName", tableName);
        query.put("ID", id);
        return InterfaceSqlConnector.selectOneFrom(query);
    }

    public void insert(final int id, final String surname, final String givenName, final String university) {
        InterfaceSqlConnector.addStudent(id, surname, givenName, university);
    }

    public void delete(final String id) {
        InterfaceSqlConnector.delete(id);
    }
}
