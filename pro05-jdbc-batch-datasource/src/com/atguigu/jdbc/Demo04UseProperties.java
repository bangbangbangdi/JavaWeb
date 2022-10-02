package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Demo04UseProperties {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream is = Demo04UseProperties.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(is);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);


        System.out.println(dataSource.getConnection());
    }

}
