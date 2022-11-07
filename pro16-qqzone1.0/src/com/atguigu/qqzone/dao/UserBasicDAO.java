package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.pojo.UserDetail;

import java.util.List;

public interface UserBasicDAO {

    UserBasic getUserBasic(String loginId,String pwd);
    List<UserBasic> getFriendList(UserBasic userBasic);
    UserBasic getUserBasicById(Integer id);

}
