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
        this.accountInfo = this.accountService.selectAll();
        this.roleInfo = this.roleService.selectAll();
        this.permissionInfo = this.permissionService.selectAll();
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
        return Boolean.FALSE;
    }

    public static Object findRolesForUser(final String user) {
        final Set<String> roles = new HashSet<String>();
        for (final UserRole cursor : cache.roleInfo) {
            if (user.equals(cursor.getUsername())) {
                roles.add(cursor.getRole_name());
            }
        }
        return roles.size() != 0 ? roles : Boolean.FALSE;
    }

    public static Object findPermissionsForRole(final String role) {
        final Set<String> permission = new HashSet<String>();
        for (final RolePermission cursor : cache.permissionInfo) {
            if (role.equals(cursor.getRole_name())) {
                permission.add(cursor.getPermission());
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