package com.atguigu.book.controller;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {

    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    public String register(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (!verifyCode.equals(kaptchaCodeObj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script language='javascript'>alert('验证码不正确');</script>");
            return "user/regist";
        }else{
            userService.register(new User(uname,pwd,email,0));
            return "user/login";
        }
    }

    public String ckUname(String uname){
        User user = userService.getUser(uname);
        if (user != null){
            // 用户名已被占用
            return "json:{'uname':'1'}";
        }else{
            // 用户名可以注册
            return "json:{'uname':'0'}";
        }
    }
}
