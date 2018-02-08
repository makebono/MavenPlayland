package com.makebono.mavenplayland.test.shirotest.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.makebono.mavenplayland.module_test.module.entities.UserAccount;

/** 
 * @ClassName: UserAccountService 
 * @Description: UserAccountService 
 * @author makebono
 * @date 2018年2月7日 下午3:05:17 
 *  
 */

public interface UserAccountService {
    @Select("select * from users")
    public List<UserAccount> selectAll();
}
