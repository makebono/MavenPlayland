package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Entity;

/** 
 * @ClassName: Resident 
 * @Description: A pojo for Dozer mappting testing. Along with BCResident with direct mapping.
 * @author makebono
 * @date 2018年1月10日 上午10:09:00 
 *  
 */
@Entity
public class Resident {
    private String id;
    private String name;
    private String age;

    // Yes, for sure the fields don't need to be perfect matched on number between classes. If you did not specify a
    // field, and causing unmatched number of fields between mapping classes, mapper will ignore the surplus field(s).
    private String gender;

    public void setId(final String id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    @Override
    public String toString() {
        return "Resident " + this.name + "(" + this.id + ", " + this.gender + "). Age: " + this.age + ".";
    }
}
