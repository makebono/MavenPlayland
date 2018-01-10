package com.makebono.mavenplayland.test.dozermappingtest;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;

import com.makebono.mavenplayland.module_test.module.entities.BCResident;
import com.makebono.mavenplayland.module_test.module.entities.CoquitlamResident;
import com.makebono.mavenplayland.module_test.module.entities.Resident;

/** 
 * @ClassName: Demo 
 * @Description: Demo
 * @author makebono
 * @date 2018年1月10日 上午10:21:57 
 *  
 */
public class DozerDemo {
    private static Resident rex;
    static {
        rex = new Resident();
        rex.setAge("28");
        rex.setId("1234567");
        rex.setName("レックス");
    }

    public static void directMapping() {
        final BCResident rexBc = new DozerBeanMapper().map(rex, BCResident.class);
        System.out.println(rex);
        System.out.println(rexBc);
    }

    public static void xmlConfigurationMapping() {
        final DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozermapper\\residentmapper.xml"));
        final CoquitlamResident rexCoq = mapper.map(rex, CoquitlamResident.class);
        System.out.println(rex);
        System.out.println(rexCoq);
    }
}
