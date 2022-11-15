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
        // 重新覆盖一下friend中的信息,(为什么不覆盖userBasic中的数据?因为main.html页面中迭代的是friend这个key中的数据)
        // 并且userBasic这个key已经被使用了,用于区别当前的日志列表是否是我的日志(会影响权限)
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }

}
