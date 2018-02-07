package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: UserAccount 
 * @Description: UserAccount 
 * @author makebono
 * @date 2018年2月7日 下午2:06:17 
 *  
 */
@Table(name = "useraccounts")
public class UserAccount {
    @Id
    @Column(name = "ACCOUNT", columnDefinition = "VARCHAR")
    private String ACCOUNT;

    @Column(name = "SECURITYKEY", columnDefinition = "VARCHAR")
    private String SECURITYKEY;

    @Column(name = "ROLE", columnDefinition = "VARCHAR")
    private String ROLE;

    public void setAccount(final String ACCOUNT) {
        this.ACCOUNT = ACCOUNT;
    }

    public void setSecurityKey(final String SECURITYKEY) {
        this.SECURITYKEY = SECURITYKEY;
    }

    public void setRole(final String ROLE) {
        this.ROLE = ROLE;
    }

    public String getAccount() {
        return this.ACCOUNT;
    }

    public String getSecurityKey() {
        return this.SECURITYKEY;
    }

    public String getRole() {
        return this.ROLE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.ACCOUNT + "(" + this.SECURITYKEY + ") , " + this.ROLE + ".");
        return sb.toString();
    }
}
