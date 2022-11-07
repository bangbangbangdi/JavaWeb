package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {

    List<Reply> getReplyByTopicId(Integer topicId);
    void delReply(Integer replyId);
    void addReply(Reply reply);
    void delReplyList(Topic topic);

}
