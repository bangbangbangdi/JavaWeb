package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

public interface UserService {

    User login(String uname,String pwd);
    void register(User user);
    User getUser(String name);

}
