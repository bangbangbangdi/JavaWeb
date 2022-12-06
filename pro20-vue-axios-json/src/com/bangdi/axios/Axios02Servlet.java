package com.bangdi.axios;

import com.bangdi.pojo.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
// 这里想要展示的是如何处理JSON类型的参数
// 也就是如何将JSON格式的字符串转为普通的Java对象的问题
@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.由于请求体数据有可能很大,所以Servlet标准在设计API的时候要求我们通过输入流来读取
        BufferedReader reader = req.getReader();

        // 2.创建StringBuilder对象来累加存储从请求体中读取到的每一行
        StringBuffer builder = new StringBuffer("");

        // 3.声明临时变量
        String str = null;

        // 4.循环读取
        while((str=reader.readLine())!=null){
            builder.append(str);
        }

        // 5.关闭流
        reader.close();

        // 6.累加的结果就是整个请求体
        String reqBody = builder.toString();

        // 7.创建Gson对象用于解析JSON字符串
        Gson gson = new Gson();

        // 8.将JSON对象字符串还原为Java对象
        User user = gson.fromJson(reqBody, User.class);

        System.out.println("user = " + user);

    }
}
