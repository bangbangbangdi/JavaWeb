package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {

    List<TopicService> getTopicService(UserBasic userBasic);
    Topic getTopicById(Integer id);
    void delTopic(Integer id);

}
