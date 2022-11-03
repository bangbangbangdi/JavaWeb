package com.atguigu.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String URL = "jdbc:mysql://localhost:3306/atguigudb";
    final static String USER = "root";
//    final static String PWD = "abc123";
    final static String PWD = "jiangui.1";

    private static Connection createConn(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            return ;
        }
        if (!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }
}
