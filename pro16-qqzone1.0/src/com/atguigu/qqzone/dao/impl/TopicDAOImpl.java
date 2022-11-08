package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("SELECT * FROM t_topic WHERE author = ?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        super.executeUpdate("INSERT INTO t_topic(id,title,content,topicDate,author) VALUES(0,?,?,?,?)", topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor().getId());
    }

    @Override
    public void delTopic(Topic topic) {
        super.executeUpdate("DELETE FROM t_topic WHERE id = ?", topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return super.load("SELECT * FROM t_topic WHERE id = ?", id);
    }
}
