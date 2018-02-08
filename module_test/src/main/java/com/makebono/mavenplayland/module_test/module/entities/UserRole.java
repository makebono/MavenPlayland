package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: UserRoles 
 * @Description: UserRoles 
 * @author makebono
 * @date 2018年2月8日 上午9:56:12 
 *  
 */
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR")
    private String username;

    @Column(name = "role_name", columnDefinition = "VARCHAR")
    private String role_name;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setEole_name(final String password_salt) {
        this.role_name = password_salt;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole_name() {
        return this.role_name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.username + "(" + this.id + ", " + this.role_name + ")");
        return sb.toString();
    }
}
