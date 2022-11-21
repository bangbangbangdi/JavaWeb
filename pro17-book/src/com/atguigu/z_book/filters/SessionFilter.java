package com.atguigu.z_book.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
// 这个Filter的作用在于过滤掉一些不允许的请求 例如 直接跳过用户登陆阶段直接去访问购物车的页面等等
// 那网站内的页面是如何进行跳转的呢？每次有请求进来时，先去判断一下session里是否有当前的user，如果有就放行

@WebFilter(urlPatterns = {"*.do","*.html"},
        initParams = {
            @WebInitParam(name = "white",
                        value = "/pro17/page.do?operate=page&page=user/login,/pro17/user.do?null"
            )
        })
public class SessionFilter implements Filter {

    List<String> whiteList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String white = filterConfig.getInitParameter("white");
        String[] whiteArr = white.split(",");
        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = uri + "?" + queryString;
        if (whiteList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession();
            Object currUser = session.getAttribute("currUser");
            if (currUser == null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
