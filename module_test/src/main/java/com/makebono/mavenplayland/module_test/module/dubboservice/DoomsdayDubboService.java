package com.makebono.mavenplayland.module_test.module.dubboservice;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.makebono.mavenplayland.module_test.module.dubbointerface.IDoomsdayDubboService;
import com.makebono.mavenplayland.module_test.module.service.DoomsdayService;

/** 
 * @ClassName: DoomsdayDubboService 
 * @Description: DoomsdayDubboService, simply a dubbo shell of DommsdayService
 * @author makebono
 * @date 2018年2月2日 上午9:20:17 
 *  
 */
@Service(interfaceClass = IDoomsdayDubboService.class)
public class DoomsdayDubboService implements IDoomsdayDubboService {

    static {
        System.out.println("Dubbo service " + DoomsdayDubboService.class + " on your command.");
    }

    @Autowired
    private DoomsdayService service;

    @Override
    public String calcDoomsday(final String... date) throws Throwable {
        if (date.length > 1) {
            throw new IllegalArgumentException("Input should be either A date or empty for current date");
        }

        if (date.length == 0) {
            final Calendar currentDate = Calendar.getInstance();
            final int year = currentDate.get(Calendar.YEAR);
            final int month = currentDate.get(Calendar.MONTH) + 1;
            final int day = currentDate.get(Calendar.DAY_OF_MONTH);

            final StringBuilder dateInString = new StringBuilder();
            dateInString.append(year);
            dateInString.append(month < 10 ? ("0" + month) : (month));
            dateInString.append(day < 10 ? ("0" + day) : (day));

            return this.service.doomsday(dateInString.toString());
        }

        return this.service.doomsday(date[0]);
    }

}
