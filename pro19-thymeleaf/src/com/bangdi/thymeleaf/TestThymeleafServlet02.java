package com.bangdi.thymeleaf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TestThymeleafServlet02")
public class TestThymeleafServlet02 extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = "target";
        req.setAttribute("reqAttrName","<span>hello-value</span>");
        super.processTemplate(viewName,req,resp);
    }
}
