package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);
            // 当前系统引用对象需要手动赋值
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            // 在userBasic中存储的是登陆者的信息
            // friendList的存储的是当前进入的是谁的空间(对应日志,以及一些好友权限之类的)
            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friendList", friendList);
            session.setAttribute("friend",userBasic);
            return "index";
        } else {
            return "login";
        }
    }

    public String friend(Integer id, HttpSession session) {
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currFriend);
        currFriend.setTopicList(topicList);
        session.setAttribute("friend", currFriend);
        return "index";
    }

}
