/**Note:
 * Purpose of this cache is to load account, role, permission info at start-up. So it will save bunch of times than querying the database
 * for each security related manipulation. A reload method here could be recall any time needed for updating cache from database. In this
 * specific situation, I reload once each time when cannot find an input user. And in dispatcher servlet there's a config for reloading 
 * every 5 mins after start-up. 
 */
package com.makebono.mavenplayland.module_test.common.system.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private Map<String, UserAccount> accountInfo;
    private Map<String, UserRole> roleInfo;
    private Map<String, RolePermission> permissionInfo;

    private SecurityInfoCache() {}

    public void init() {
        cache = this;
        cache.accountInfo = cache.loadAccount();
        cache.roleInfo = cache.loadRole();
        cache.permissionInfo = cache.loadPermission();
    }

    public void reload() {
        cache.accountInfo = cache.loadAccount();
        cache.roleInfo = cache.loadRole();
        cache.permissionInfo = cache.loadPermission();
        System.out.println("Security cache reloaded.");
    }

    public Map<String, UserAccount> loadAccount() {
        final Map<String, UserAccount> info = new HashMap<String, UserAccount>();
        final List<UserAccount> accounts = cache.accountService.selectAll();
        for (final UserAccount candidate : accounts) {
            info.put(candidate.getUsername(), candidate);
        }
        return info;
    }

    public Map<String, UserRole> loadRole() {
        final Map<String, UserRole> info = new HashMap<String, UserRole>();
        final List<UserRole> roles = cache.roleService.selectAll();
        for (final UserRole candidate : roles) {
            info.put(candidate.getUsername(), candidate);
        }
        return info;
    }

    public Map<String, RolePermission> loadPermission() {
        final Map<String, RolePermission> info = new HashMap<String, RolePermission>();
        final List<RolePermission> permissions = cache.permissionService.selectAll();
        for (final RolePermission candidate : permissions) {
            info.put(candidate.getRole_name(), candidate);
        }
        return info;
    }

    public static Map<String, UserAccount> getAccountInfo() {
        return cache.accountInfo;
    }

    public static Map<String, UserRole> getRoleInfo() {
        return cache.roleInfo;
    }

    public static Map<String, RolePermission> getPermissionInfo() {
        return cache.permissionInfo;
    }

    public static Object findUser(final String account) {
        UserAccount candidate = cache.accountInfo.get(account);

        if (candidate != null) {
            return candidate;
        }

        cache.reload();

        candidate = cache.accountInfo.get(account);

        if (candidate != null) {
            return candidate;
        }

        return Boolean.FALSE;
    }

    // Add role not defined exception.
    public static Object findRolesForUser(final String user) {
        final Set<String> roles = new HashSet<String>();
        final String roleString = cache.roleInfo.get(user).getRole_name();

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

    // Add permission not defined exception.
    public static Object findPermissionsForRole(final String role) {
        final Set<String> permission = new HashSet<String>();
        final String permissionString = cache.permissionInfo.get(role).getPermission();

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