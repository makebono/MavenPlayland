package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Entity;

/** 
 * @ClassfullName: CoquitlamResident 
 * @Description: Different field fullName with other two entities. Need a configuration xml.
 * @author makebono
 * @date 2018年1月10日 上午10:24:40 
 *  
 */
@Entity
public class CoquitlamResident {
    private long coquitlamId;
    private String fullName;
    private short currentAge;

    public void setCoquitlamId(final long coquitlamId) {
        this.coquitlamId = coquitlamId;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setCurrentAge(final short currentAge) {
        this.currentAge = currentAge;
    }

    public long getCoquitlamId() {
        return this.coquitlamId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public short getCurrentAge() {
        return this.currentAge;
    }

    @Override
    public String toString() {
        return "Coquitlam resident " + this.fullName + "(" + this.coquitlamId + "). Current age: " + this.currentAge
                + ".";
    }
}
