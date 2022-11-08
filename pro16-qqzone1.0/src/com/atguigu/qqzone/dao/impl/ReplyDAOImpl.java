package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    @Override
    public List<Reply> getReplyList(Topic topic) {
        return super.executeQuery("SELECT * FROM t_reply WHERE topic = ?",topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        super.executeUpdate("INSERT INTO t_reply(id,content,replyDate,author,topic) VALUES(0,?,?,?,?)",reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer replyId) {
        super.executeUpdate("DELETE FROM t_reply WHERE id = ?",replyId);
    }

    @Override
    public Reply getReply(Integer id) {
        return super.load("SELECT * FROM t_reply WHERE id = ?",id);
    }
}
