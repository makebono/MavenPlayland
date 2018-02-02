package com.makebono.mavenplayland.module_test.module.dubbointerface;

import java.security.InvalidParameterException;

/** 
 * @ClassName: IAlgorithmDubboService 
 * @Description: IAlgorithmDubboService 
 * @author makebono
 * @date 2018年2月2日 下午2:38:52 
 *  
 */
public interface IAlgorithmDubboService {

    void toh(int home, int target, int level) throws Throwable;

    String eightQueens();

    long fibonacci(int n);

    long fibonacci2(int n);

    String doomsday(String date) throws InvalidParameterException;

}
