package com.makebono.mavenplayland.module_test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.makebono.mavenplayland.module_test.module.entities.Student;

/** 
 * @ClassName: MappingInterface 
 * @Description: Interface annotation based SQL query 
 * @author makebono
 * @date 2017年12月4日 上午8:45:33 
 *  
 */
public interface MappingInterface {
    @Insert("insert into maven_test (ID, GIVENNAME, SURNAME, UNIVERSITY) values(#{ID},#{GIVENNAME},#{SURNAME},#{UNIVERSITY})")
    public void insert(Student student);

    @Select("select * from maven_test where ID=#{ID}")
    public Student selectOne(String id);

    @Select("select * from ${tableName} where ID=${ID}")
    public Student selectOneFrom(Map<String, Object> Query);

    @Select("select * from maven_test")
    public List<Student> selectAll();

    @Delete("delete from maven_test where ID=#{id}")
    public void delete(String id);

}
