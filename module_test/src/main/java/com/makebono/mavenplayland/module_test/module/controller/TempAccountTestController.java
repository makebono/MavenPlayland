package com.makebono.mavenplayland.module_test.module.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.makebono.mavenplayland.module_test.common.system.security.SecurityInfoCache;
import com.makebono.mavenplayland.module_test.module.entities.UserAccount;

/** 
 * @ClassName: TempAccountTestController 
 * @Description: TempAccountTestController 
 * @author makebono
 * @date 2018年2月7日 下午2:53:39 
 *  
 */
@Controller
public class TempAccountTestController {

    @RequestMapping("/securityInfo")
    @ResponseBody
    public List<UserAccount> getInfo() {
        return SecurityInfoCache.getInfo();
    }
}
