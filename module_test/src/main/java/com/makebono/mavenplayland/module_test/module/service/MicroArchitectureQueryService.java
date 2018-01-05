package com.makebono.mavenplayland.module_test.module.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.connector.InterfaceSqlConnector;
import com.makebono.mavenplayland.module_test.module.entities.MicroArchitecture;

/** 
 * @ClassName: MicroArchitectureQueryService 
 * @Description: MicroArchitectureQueryService
 * @author makebono
 * @date 2018年1月5日 上午10:31:29 
 *  
 */
@Service
public class MicroArchitectureQueryService {
    public List<MicroArchitecture> selectAll() {
        return InterfaceSqlConnector.selectAllMicroArchitecture();
    }

    public MicroArchitecture selectOne(final String MODEL) {
        return InterfaceSqlConnector.selectMicroArchitecture(MODEL);
    }
}
