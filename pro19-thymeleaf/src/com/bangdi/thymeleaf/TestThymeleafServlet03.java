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

@WebServlet("/TestThymeleafServlet03")
public class TestThymeleafServlet03 extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("I come in");
        String viewName = "target";
        String reqAttrName = "helloRequestAttr";
        String reqAttrValue = "helloRequestAttr-Value";
        req.setAttribute(reqAttrName,reqAttrValue);

        req.setAttribute("aNotEmptyList", Arrays.asList("aa","bb","cc"));
        req.setAttribute("anEmptyList",new ArrayList<>());

        String sessionAttrName = "helloSessionAttr";
        String sessionAttrValue = "helloSessionAttr-Value";
        HttpSession session = req.getSession();
        session.setAttribute(sessionAttrName,sessionAttrValue);

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("helloAppAttr","helloAppAttr-Value");

        System.out.println("servletContext.getAttribute(\"helloAppAttr\") = " + servletContext.getAttribute("helloAppAttr"));
        super.processTemplate(viewName,req,resp);
    }
}
