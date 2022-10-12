package com.atguigu.myssm.myspringmvc;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> list = fruitDAO.getFruitList();
        list.forEach(System.out::println);
    }
}
