package com.makebono.mavenplayland.module_test.service;

import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.connector.SqlConnector;
import com.makebono.mavenplayland.module_test.entities.Student;

/** 
 * @ClassName: QueryService 
 * @Description: Simple query service
 * @author makebono
 * @date 2017年11月30日 下午6:08:54 
 *  
 */

@Service
public class QueryService {
    public Student selectById(final int id) {
        return SqlConnector.selectById(id);
    }
}
