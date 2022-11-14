package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {

    List<Topic> getTopicService(UserBasic userBasic);
    Topic getTopicById(Integer id);

    // 根据id获取指定的topic信息,包含topic关联作者信息
    public Topic getTopic(Integer id);

    void delTopic(Integer id);

}
