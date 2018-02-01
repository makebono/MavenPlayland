package com.makebono.mavenplayland.module_test.common.interceptorhandler;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * @ClassName: BonoInterceptorHandler 
 * @Description: Implementation of customized interceptor handler
 * @author makebono
 * @date 2018年1月29日 下午1:58:54 
 *  
 */
public class BonoInterceptorHandler extends HandlerInterceptorAdapter {
    static {
        System.out.println(BonoInterceptorHandler.class.getName() + " on your command.");
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {
        final Calendar date = Calendar.getInstance();
        final int year = date.get(Calendar.YEAR);
        final int month = date.get(Calendar.MONTH) + 1;
        final int day = date.get(Calendar.DAY_OF_MONTH);

        final StringBuilder dateInString = new StringBuilder();
        dateInString.append(year);
        dateInString.append(month < 10 ? ("0" + month) : (month));
        dateInString.append(day < 10 ? ("0" + day) : (day));

        System.out.println("Pre handling, adding attribute into request: (\"date\", " + dateInString.toString() + ")");
        request.setAttribute("date", dateInString.toString());
        request.setAttribute("handledBy", this.getClass().getName());
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {
        System.out.println("Request handled by: " + request.getAttribute("handledBy"));
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
            final Object handler, final Exception ex) throws Exception {
        System.out.println("All request processed.");
    }
}
