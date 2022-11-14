package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO;

    @Override
    public UserBasic login(String loginId, String pwd) {
        // 从这里也可以看出来分层的好处
        // DAO层的方法名可以只描述它要做的事情,而service会用它实现什么功能它是不管的
        return userBasicDAO.getUserBasic(loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        // 此处获得的UserBasics列表中的UserBasic对象是只有id属性的空壳
        // 话说这里为啥不获取完整的UserBasic对象呢?
        // 之前以为是出于安全考虑
        // 现在看来,最后返回的friend列表也是含有好友的用户名和密码的
        // 不过绕这一下确实给我们控制返回内容的空间
        List<UserBasic> userBasics = userBasicDAO.getFriendList(userBasic);
        // 这个是真正要显示到页面上的好友列表
        List<UserBasic> friendList = new ArrayList<>(userBasics.size());
        for (UserBasic user : userBasics) {
            // 通过主键获得完整的对象
            UserBasic friend = userBasicDAO.getUserBasicById(user.getId());
            friendList.add(friend);
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }
}
