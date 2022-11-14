package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicService(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        // 此处基于Topic表当中的某条数据而生成的Topic对象而言,引用类型是没有办法通过表直接赋值的
        // 因此需要在获取某个Topic对象时,手动调用set方法对引用对象进行赋值
        Topic topic = topicDAO.getTopic(id);
        List<Reply> replyList = replyService.getReplyByTopicId(topic.getId());
        topic.setReplyList(replyList);

        return topic;
    }

    // 该方法与getTopicById区别在于一个是关联Reply
    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);


        return null;
    }

    // 业务分析:删除某条日志时,也应该将所有关联的回复信息一并删除
    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        // 这里加上的非空检查是因为ReplyDAO在实现的时候没有做非空检查,因此需要从上游程序确保传过去的值一定不能是空
        if (topic != null){
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }
}
