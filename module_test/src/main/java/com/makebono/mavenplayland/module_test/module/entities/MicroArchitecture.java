package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: MicroArchitecture 
 * @Description: MicroArchitecture
 * @author makebono
 * @date 2018年1月5日 上午10:20:02 
 *  
 */

@Table(name = "maven_test2")
public class MicroArchitecture {
    @Id
    @Column(name = "MODEL", columnDefinition = "VARCHAR")
    private String MODEL;

    @Column(name = "CORE", columnDefinition = "VARCHAR")
    private String CORE;

    @Column(name = "BRAND", columnDefinition = "VARCHAR")
    private String BRAND;

    public void setModel(final String MODEL) {
        this.MODEL = MODEL;
    }

    public void setCore(final String CORE) {
        this.CORE = CORE;
    }

    public void setBrand(final String BRAND) {
        this.BRAND = BRAND;
    }

    public String getModel() {
        return this.MODEL;
    }

    public String getCore() {
        return this.CORE;
    }

    public String getBrand() {
        return this.BRAND;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.MODEL + "(" + this.CORE + ") from " + this.BRAND + ".");
        return sb.toString();
    }
}
