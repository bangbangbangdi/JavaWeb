package com.atguigu.fruit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo01Batch {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/atguigudb";
    static final String USER = "root";
    static final String PWD = "abc123";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USER, PWD);
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            psmt.setObject(1, "咩咩");
            psmt.setObject(2, i);
            psmt.setObject(3, i);
            psmt.setObject(4, "猪猪" + i);

            psmt.addBatch();

        }

        int[] count = psmt.executeBatch();

        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }

        psmt.close();
        conn.close();
    }
}
