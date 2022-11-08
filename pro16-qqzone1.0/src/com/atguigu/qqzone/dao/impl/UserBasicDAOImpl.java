package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("SELECT * FROM t_user_basic WHERE loginId = ? AND pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        return super.executeQuery("SELECT * FROM t_user_basic WHERE id IN (SELECT fid FROM t_friend WHERE uid = ?)", userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return super.load("SELECT * FROM t_user_basic WHERE id = ?", id);
    }
}
