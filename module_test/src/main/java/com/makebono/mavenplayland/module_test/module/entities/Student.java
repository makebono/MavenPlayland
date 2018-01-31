package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: Student 
 * @Description: Sql table: maven_test
 * @author makebono
 * @date 2017年11月29日 下午3:35:38 
 *  
 */

@Table(name = "maven_test")
public class Student {
    @Id
    @Column(name = "ID", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "GIVENNAME", columnDefinition = "VARCHAR")
    private String GIVENNAME;

    @Column(name = "SURNAME", columnDefinition = "VARCHAR")
    private String SURNAME;

    @Column(name = "UNIVERSITY", columnDefinition = "VARCHAR")
    private String UNIVERSITY;

    public void setId(final long id) {
        this.ID = id;
    }

    public void setGivenName(final String givenName) {
        this.GIVENNAME = givenName;
    }

    public void setSurname(final String surname) {
        this.SURNAME = surname;
    }

    public void setUniversity(final String university) {
        this.UNIVERSITY = university;
    }

    public long getId() {
        return this.ID;
    }

    public String getGivenName() {
        return this.GIVENNAME;
    }

    public String getSurname() {
        return this.SURNAME;
    }

    public String getUniversity() {
        return this.UNIVERSITY;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.GIVENNAME + " " + this.SURNAME + "(" + this.ID + ") from " + this.UNIVERSITY + ".");
        return sb.toString();
    }
}
