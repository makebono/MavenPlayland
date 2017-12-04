package com.makebono.mavenplayland.module_test.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.connector.SqlConnector;
import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: QueryService 
 * @Description: Simple query service
 * @author makebono
 * @date 2017年11月30日 下午6:08:54 
 *  
 */

@Service
public class QueryService {
    public Student selectById(final String id) {
        return SqlConnector.selectById(id);
    }

    public List<Student> selectAll() {
        return SqlConnector.selectAll();
    }

    public Student selectOneFrom(final String tableName, final String id) {
        final Map<String, Object> query = new HashMap<String, Object>();
        query.put("tableName", tableName);
        query.put("ID", id);
        return SqlConnector.selectOneFrom(query);
    }

    public void insert(final int id, final String surname, final String givenName, final String university) {
        SqlConnector.addStudent(id, surname, givenName, university);
    }

    public void delete(final String id) {
        SqlConnector.delete(id);
    }
}
