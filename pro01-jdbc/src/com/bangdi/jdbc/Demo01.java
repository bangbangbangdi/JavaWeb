package com.bangdi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/atguigudb";
        String user = "root";
        String pwd = "abc123";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
    }

}
