package com.atguigu.jdbc.dao;

import com.atguigu.jdbc.pojo.Fruit;

import java.util.List;

public interface FruitDAO {

    List<Fruit> getFruitList();

    boolean addFruit(Fruit fruit);

    boolean updateFruit(Fruit fruit);

    Fruit getFruitByFname(String fname);

    boolean delFruit(String fname);

}
