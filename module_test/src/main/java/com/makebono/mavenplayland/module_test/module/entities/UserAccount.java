package com.makebono.mavenplayland.module_test.module.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName: UserUsername 
 * @Description: UserUsername 
 * @author makebono
 * @date 2018年2月7日 下午2:06:17 
 *  
 */
@Table(name = "users")
public class UserAccount {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR")
    private String password;

    @Column(name = "password_salt", columnDefinition = "VARCHAR")
    private String password_salt;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setPassword_salt(final String password_salt) {
        this.password_salt = password_salt;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPassword_salt() {
        return this.password_salt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.username + "(" + this.id + ", " + this.password + ", " + this.password_salt + ")");
        return sb.toString();
    }
}
