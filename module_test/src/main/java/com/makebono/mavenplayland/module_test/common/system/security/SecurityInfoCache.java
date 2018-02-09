/**Note:
 * Purpose of this cache is to load account, role, permission info at start-up. So it will save bunch of times than querying the database
 * for each security related manipulation will cost a lot. A reload method is here, can be recall any time needed for updating cache from
 * database. In this specific situation, I reload once each time when cannot find an input user. 
 */
package com.makebono.mavenplayland.module_test.common.system.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.service.BaseMapperService;
import com.makebono.mavenplayland.module_test.module.entities.RolePermission;
import com.makebono.mavenplayland.module_test.module.entities.UserAccount;
import com.makebono.mavenplayland.module_test.module.entities.UserRole;

/** 
 * @ClassName: SecurityInfoCache 
 * @Description: Load security informations at startup. 
 * @author makebono
 * @date 2018年2月7日 下午2:12:48 
 *  
 */
public class SecurityInfoCache {

    @Autowired
    private UserAccountService accountService;

    @Autowired
    private UserRoleService roleService;

    @Autowired
    private RolePermissionService permissionService;

    private static SecurityInfoCache cache;
    private List<UserAccount> accountInfo;
    private List<UserRole> roleInfo;
    private List<RolePermission> permissionInfo;

    private SecurityInfoCache() {}

    public void init() {
        cache = this;
        cache.accountInfo = cache.accountService.selectAll();
        cache.roleInfo = cache.roleService.selectAll();
        cache.permissionInfo = cache.permissionService.selectAll();
    }

    public void reload() {
        cache.accountInfo = cache.accountService.selectAll();
        cache.roleInfo = cache.roleService.selectAll();
        cache.permissionInfo = cache.permissionService.selectAll();
        System.out.println("Security cache reloaded.");
    }

    public static List<UserAccount> getAccountInfo() {
        return cache.accountInfo;
    }

    public static List<UserRole> getRoleInfo() {
        return cache.roleInfo;
    }

    public static List<RolePermission> getPermissionInfo() {
        return cache.permissionInfo;
    }

    public static Object findUser(final String account) {
        for (final UserAccount cursor : cache.accountInfo) {
            if (account.equals(cursor.getUsername())) {
                return cursor;
            }
        }

        cache.reload();

        for (final UserAccount cursor : cache.accountInfo) {
            if (account.equals(cursor.getUsername())) {
                return cursor;
            }
        }

        return Boolean.FALSE;
    }

    public static Object findRolesForUser(final String user) {
        final Set<String> roles = new HashSet<String>();
        String roleString = "";
        for (final UserRole cursor : cache.roleInfo) {
            if (user.equals(cursor.getUsername())) {
                roleString = cursor.getRole_name();
                break;
            }
        }

        if (roleString.length() != 0) {
            int start = 0;
            int end = 0;

            for (int i = 0; i < roleString.length(); i++) {
                if (roleString.charAt(i) == ',') {
                    end = i;
                    roles.add(roleString.substring(start, end));
                    start = i + 1;
                }

                if (i == roleString.length() - 1) {
                    roles.add(roleString.substring(start, roleString.length()));
                }
            }
        }

        return roles.size() != 0 ? roles : Boolean.FALSE;
    }

    public static Object findPermissionsForRole(final String role) {
        final Set<String> permission = new HashSet<String>();
        String permissionString = "";
        for (final RolePermission cursor : cache.permissionInfo) {
            if (role.equals(cursor.getRole_name())) {
                permissionString = cursor.getPermission();
                break;
            }
        }

        if (permissionString.length() != 0) {
            int start = 0;
            int end = 0;

            for (int i = 0; i < permissionString.length(); i++) {
                if (permissionString.charAt(i) == ',') {
                    end = i;
                    permission.add(permissionString.substring(start, end));
                    start = i + 1;
                }

                if (i == permissionString.length() - 1) {
                    permission.add(permissionString.substring(start, permissionString.length()));
                }
            }
        }

        return permission.size() != 0 ? permission : Boolean.FALSE;
    }
}

@Service
class UserAccountService extends BaseMapperService<UserAccount> {}

@Service
class UserRoleService extends BaseMapperService<UserRole> {}

@Service
class RolePermissionService extends BaseMapperService<RolePermission> {}