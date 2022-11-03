package com.atguigu.myssm.trans;

import com.atguigu.myssm.basedao.ConnUtil;

import java.sql.SQLException;

public class TransactionManager {
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        ConnUtil.getConn().commit();
    }

    public static void rollback() throws SQLException {
        ConnUtil.getConn().rollback();
    }
}
