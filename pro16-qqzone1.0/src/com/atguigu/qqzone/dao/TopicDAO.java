package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {

    List<TopicDAO> getTopicList(UserBasic userBasic);
    void addTopic(Topic topic);
    void delTopic(Topic topic);
    Topic getTopic(Integer id);

}
