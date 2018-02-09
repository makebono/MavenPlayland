package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: RolePermission 
 * @Description: RolePermission 
 * @author makebono
 * @date 2018年2月8日 下午2:10:56 
 *  
 */
@Table(name = "roles_permissions")
public class RolePermission {
    @Id
    @Column(name = "role_name", columnDefinition = "VARCHAR")
    private String role_name;

    @Column(name = "permission", columnDefinition = "VARCHAR")
    private String permission;

    public void setRole_name(final String role_name) {
        this.role_name = role_name;
    }

    public void setPermission(final String permission) {
        this.permission = permission;
    }

    public String getRole_name() {
        return this.role_name;
    }

    public String getPermission() {
        return this.permission;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.role_name + "(" + this.permission + ")");
        return sb.toString();
    }
}
