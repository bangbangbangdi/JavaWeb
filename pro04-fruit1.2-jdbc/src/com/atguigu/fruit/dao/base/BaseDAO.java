package com.atguigu.fruit.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;
    protected Class entityClass;


    final String DRIVER = "com.mysql.cj.jdbc.Driver";
    final String URL = "jdbc:mysql://localhost:3306/atguigudb";
    //    final String URL = "jdbc:mysql://localhost:3306/atguigudb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    final String USER = "root";
    final String PWD = "abc123";

    public BaseDAO() {
        Type genericType = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        Type actualType = actualTypeArguments[0];
        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        boolean insertFlg = false;
        insertFlg = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            if (insertFlg) {
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = conn.prepareStatement(sql);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();

//            rs = psmt.getGeneratedKeys();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//            return insertFlg ? psmt.getGeneratedKeys().getInt(1) : count;
            rs = psmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
//                return ((Long)rs.getLong(1)).intValue();
            }
            return count ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return 0;
    }

    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getDeclaredField(property);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (psmt != null && params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    protected Object[] executeComplexQuery(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    columnValueArr[i] = rs.getObject(i + 1);
                }
            }
            return columnValueArr;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return null;
    }

    protected T load(String sql, Object... params) {
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T entity = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return null;
    }

    protected List<T> executeQuery(String sql, Object... params) {
        ArrayList<T> list = new ArrayList<>();
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int columnCount = rsmt.getColumnCount();
            while (rs.next()) {
                T entity = (T) entityClass.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmt.getColumnName(i + 1);
                    Object columnValue = rs.getObject(i + 1);
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            close(rs, psmt, conn);
        }
        return list;
    }

}
