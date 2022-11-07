package com.atguigu.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static Properties properties;
    private static DataSource dataSource;

    static{
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties = new Properties();
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection createConn(){
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
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
        return conn;
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            return ;
        }
        if (!conn.isClosed()){
            conn.close();
            threadLocal.remove();
        }
    }

}
