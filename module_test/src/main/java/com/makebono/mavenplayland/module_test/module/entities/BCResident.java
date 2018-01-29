package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Entity;

/** 
 * @Classname: BCResident 
 * @Description: A pojo for Dozer mapping testing. Along with Resident with direct mapping.
 * @author makebono
 * @date 2018年1月10日 上午10:09:00 
 *  
 */
@Entity
public class BCResident {
    private long id;
    private String name;
    private short age;

    public void setid(final long id) {
        this.id = id;
    }

    public void setname(final String name) {
        this.name = name;
    }

    public void setage(final short age) {
        this.age = age;
    }

    public long getid() {
        return this.id;
    }

    public String getname() {
        return this.name;
    }

    public short getage() {
        return this.age;
    }

    @Override
    public String toString() {
        return "BC resident " + this.name + "(" + this.id + "). Age: " + this.age + ".";
    }
}
