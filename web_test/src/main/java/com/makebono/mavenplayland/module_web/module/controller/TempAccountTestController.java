package com.makebono.mavenplayland.module_web.module.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.common.system.security.SecurityInfoCache;

/** 
 * @ClassName: TempAccountTestController 
 * @Description: TempAccountTestController 
 * @author makebono
 * @date 2018年2月7日 下午2:53:39 
 *  
 */
@SuppressWarnings(value = { "rawtypes", "unchecked" })
@Controller
public class TempAccountTestController {
    final private static String path = "/securityInfo";

    @RequestMapping(value = path)
    @ResponseBody
    public Object getInfo() {

        final List failed = new ArrayList();
        try {
            final List result = new ArrayList();
            result.addAll(SecurityInfoCache.getAccountInfo().values());
            result.addAll(SecurityInfoCache.getRoleInfo().values());
            result.addAll(SecurityInfoCache.getPermissionInfo().values());

            System.out.println(result);

            return result;
        }
        catch (final Exception e) {
            failed.add("Access denied, message: " + e.getMessage());
            return failed;
        }
    }

    @RequestMapping(value = "permission")
    @ResponseBody
    public Set getPermission(final HttpServletRequest request) {
        final String role = request.getParameter("role");
        return (Set) SecurityInfoCache.findPermissionsForRole(role);
    }
}
