package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class Demo05UseProperties {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream is = Demo05UseProperties.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(is);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);


        System.out.println(dataSource.getConnection());
    }

}
