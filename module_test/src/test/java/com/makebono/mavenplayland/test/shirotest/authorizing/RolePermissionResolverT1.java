package com.makebono.mavenplayland.test.shirotest.authorizing;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/** 
 * @ClassName: RolePermissionResolverT1 
 * @Description: RolePermissionResolverT1 
 * @author makebono
 * @date 2018年2月8日 上午9:14:50 
 *  
 */
public class RolePermissionResolverT1 implements RolePermissionResolver {

    @Override
    public Collection<Permission> resolvePermissionsInRole(final String roleString) {
        if ("user".equals(roleString)) {
            return Arrays.asList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }

}
