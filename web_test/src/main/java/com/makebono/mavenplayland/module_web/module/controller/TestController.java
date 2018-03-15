package com.makebono.mavenplayland.module_web.module.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @ClassName: TestController 
 * @Description: Controller for testing
 * @author makebono
 * @date 2017年11月30日 上午11:23:01 
 *  
 */
@WebServlet(name = "TestController", urlPatterns = "/controller")
public class TestController extends HttpServlet {

    private static final long serialVersionUID = -8505905754930866057L;

    @Override
    protected void doGet(final HttpServletRequest requset, final HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet-->");
        doPost(requset, response);
    }

    @Override
    protected void doPost(final HttpServletRequest requset, final HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost-->");
        response.setContentType("text/html; charset=utf-8");
        final PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Controller</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>" + requset.getParameter("p") + "</p>");
        out.println("<p>Hello, this is TestController!</p>");
        out.println("</body>");
        out.println("</html>");

    }
}
