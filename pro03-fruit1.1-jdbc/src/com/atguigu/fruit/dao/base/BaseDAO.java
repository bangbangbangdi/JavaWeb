package com.atguigu.fruit.dao.base;

import java.sql.*;

public abstract class BaseDAO {
    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    final String DRIVER = "com.mysql.jdbc.Driver";
    final String URL = "jdbc:mysql://localhost:3306/atguigudb";
    //    final String URL = "jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String USER = "root";
    final String PWD = "abc123";

    protected Connection getConn() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i + 1, params[i]);
                }
            }
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return 0;
    }

}
