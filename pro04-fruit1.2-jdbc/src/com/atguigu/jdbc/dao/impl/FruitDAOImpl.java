package com.atguigu.jdbc.dao.impl;

import com.atguigu.jdbc.dao.FruitDAO;
import com.atguigu.jdbc.dao.base.BaseDAO;
import com.atguigu.jdbc.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        return super.executeQuery(sql);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        return super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getPrice(), fruit.getRemark()) > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ?";
        return super.executeUpdate(sql, fruit.getFcount(), fruit.getFid()) > 0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ?";
        return super.load(sql, fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname = ?";
        return super.executeUpdate(sql, fname) > 0;
    }
}
