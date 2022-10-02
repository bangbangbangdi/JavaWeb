package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

public class Demo02Druid {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/atguigudb";
    static final String USER = "root";
    static final String PWD = "abc123";

    public static void main(String[] args) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PWD);

        System.out.println(dataSource.getConnection());

    }

}
