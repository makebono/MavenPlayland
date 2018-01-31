package com.makebono.mavenplayland.module_test.module.service;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makebono.algorithms.backtracking.EightQueens;
import com.makebono.algorithms.dynamicprogramming.fibonacci.DPFibonacci;
import com.makebono.algorithms.dynamicprogramming.towerofhanoi.TowerOfHanoi;

/** 
 * @ClassName: AlgorithmService 
 * @Description: Service invoking algorithms.
 * @author makebono
 * @date 2018年1月29日 下午5:28:56 
 *  
 */
@Service
public class AlgorithmService {

    @Autowired
    private DoomsdayService doomsdayService;

    public void toh(final int home, final int target, final int level) throws Throwable {
        if (home != target && home < 4 && home > 0 && target < 4 && target > 0) {
            final TowerOfHanoi toh = new TowerOfHanoi(level);
            toh.game(home, target);
            return;
        }
        throw new IllegalArgumentException("Invalid input, please check parameters' range and order.");
    }

    public String eightQueens() {
        final String result = EightQueens.solve();
        return result;
    }

    public long fibonacci(final int n) {
        return DPFibonacci.tableAidedImplement(n).longValue();
    }

    public long fibonacci2(final int n) {
        return DPFibonacci.tableAidedImplement(n).longValue();
    }

    public String doomsday(final String date) throws InvalidParameterException {
        try {
            return this.doomsdayService.doomsday(date);
        }
        catch (final Exception e) {
            throw new InvalidParameterException("Invalid input. Please double check.");
        }
    }
}
