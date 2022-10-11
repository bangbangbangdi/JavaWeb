package com.atguigu.fruit;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Demo03UseProperties {

    public static void main(String[] args) throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream is = Demo03UseProperties.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));

        System.out.println(dataSource.getConnection());
    }

}
