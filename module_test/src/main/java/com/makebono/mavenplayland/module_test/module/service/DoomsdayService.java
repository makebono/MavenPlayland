package com.makebono.mavenplayland.module_test.module.service;

import org.springframework.stereotype.Service;

import com.makebono.algorithms.astronomy.doomsdayrule.WeekDaysCalculatorImpl;

/** 
 * @ClassName: DoomsdayService 
 * @Description: DoomsdayService
 * @author makebono
 * @date 2018年1月22日 下午1:32:06 
 *  
 */
@Service
public class DoomsdayService {
    public String doomsday(final String date) {
        // After adding self-written or third-party jar, you may need to add it to properties/Deployment Assembly of the
        // web project. Or ClassNotFoundException will occur. It is done by [Add Archive from file system].
        final WeekDaysCalculatorImpl dc = new WeekDaysCalculatorImpl();
        return dc.weekday(date);
    }
}
