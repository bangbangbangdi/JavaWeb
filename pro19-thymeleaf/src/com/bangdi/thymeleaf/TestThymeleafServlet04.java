package com.bangdi.thymeleaf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/TestThymeleafServlet04")
public class TestThymeleafServlet04 extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1,"tom",300.00));
        employeeList.add(new Employee(2,"jerry",400.00));
        employeeList.add(new Employee(3,"harry",500.00));

        req.setAttribute("employeeList",employeeList);
        req.setAttribute("user","level-1");
        super.processTemplate("target03",req,resp);
    }
}
