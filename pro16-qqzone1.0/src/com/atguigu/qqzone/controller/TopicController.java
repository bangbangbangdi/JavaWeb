package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {

    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopic(id);
        session.setAttribute("topic",topic);
        return "frames/detail";
    }

    public String delTopic(Integer id){
        topicService.delTopic(id);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session){
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        // 查找该用户的所有关联日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        // 设置关联日志列表，因为数据库中的列表没有办法实时的反应到用户的topList属性中
        userBasic.setTopicList(topicList);
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }

}
