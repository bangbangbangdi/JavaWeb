package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO = null;

    @Override
    public List<Reply> getReplyByTopicId(Integer topicId) {
        return replyDAO.getReplyList(new Topic(topicId));
    }

    @Override
    public void delReply(Integer replyId) {
        replyDAO.delReply(replyId);
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList != null){
            for (Reply reply : replyList) {
                replyDAO.delReply(reply.getId());
            }
        }
    }
}
