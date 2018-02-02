package com.makebono.mavenplayland.module_test.module.dubboservice;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.makebono.mavenplayland.module_test.module.dubbointerface.IAlgorithmDubboService;
import com.makebono.mavenplayland.module_test.module.service.AlgorithmService;;

/** 
 * @ClassName: AlgorithmDubboService 
 * @Description: AlgorithmDubboService 
 * @author makebono
 * @date 2018年2月2日 下午2:41:36 
 *  
 */
@Service(interfaceClass = IAlgorithmDubboService.class)
public class AlgorithmDubboService implements IAlgorithmDubboService {

    @Autowired
    private AlgorithmService service;

    @Override
    public void toh(final int home, final int target, final int level) throws Throwable {
        this.service.toh(home, target, level);
    }

    @Override
    public String eightQueens() {
        return this.service.eightQueens();
    }

    @Override
    public long fibonacci(final int n) {
        return this.service.fibonacci(n);
    }

    @Override
    public long fibonacci2(final int n) {
        return this.service.fibonacci2(n);
    }

    @Override
    public String doomsday(final String date) throws InvalidParameterException {
        return this.service.doomsday(date);
    }

}
