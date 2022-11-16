package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (Reply reply : replyList) {
            // 这里多绕一圈的原因在于,原本在replyList中的reply的author属性
            // 虽然也是UserBasic类型,不过此时的UserBasic实际上只有id属性
            // 但我们想要的是拥有完整属性的UserBasic,因此需要通过userBasic中的get方法通过id属性获得完整的UserBasic对象
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);
            // 上述同理
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getAuthor().getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void delReply(Integer replyId) {
        Reply reply = replyDAO.getReply(replyId);
        if (reply != null){
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            if (hostReply != null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            replyDAO.delReply(replyId);
        }
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
