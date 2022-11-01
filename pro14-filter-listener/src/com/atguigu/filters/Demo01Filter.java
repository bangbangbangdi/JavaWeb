package com.atguigu.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/demo01.do")
public class Demo01Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hello Bang");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Hello Bang2");
    }

    @Override
    public void destroy() {

    }
}
