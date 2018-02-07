package com.makebono.mavenplayland.module_test.common.system.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.service.BaseMapperService;
import com.makebono.mavenplayland.module_test.module.entities.UserAccount;

/** 
 * @ClassName: SecurityInfoCache 
 * @Description: Load security informations at startup. 
 * @author makebono
 * @date 2018年2月7日 下午2:12:48 
 *  
 */
public class SecurityInfoCache {

    @Autowired
    private UserAccountService service;

    private static SecurityInfoCache cache;
    private List<UserAccount> info;

    public void init() {
        cache = this;
        this.info = this.service.selectAll();
    }

    public static List<UserAccount> getInfo() {
        return cache.info;
    }

    public static Object findUser(final String account) {
        for (final UserAccount cursor : cache.info) {
            if (account.equals(cursor.getAccount())) {
                return cursor;
            }
        }
        return Boolean.FALSE;
    }
}

@Service
class UserAccountService extends BaseMapperService<UserAccount> {}
