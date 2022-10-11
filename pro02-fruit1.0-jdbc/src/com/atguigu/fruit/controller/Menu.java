package com.atguigu.fruit.controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);
    FruitDAO fruitDAO = new FruitDAOImpl();

    // 显示主菜单
    public int showMainMenu() {
        System.out.println("===================欢迎使用咩咩水果库存系统=====================");
        System.out.println("1.查看水果库存");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.println("============================================================");
        System.out.println("请选择 : ");
        Scanner input = new Scanner(System.in);
        int slt = input.nextInt();
        return slt;
    }

    public void showFruitList() {
        List<Fruit> fruitList = fruitDAO.getFruitList();
        System.out.println("---------------------------------------------------------------");
        System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
        if (fruitList == null || fruitList.size() <= 0) {
            System.out.println("对不起,库存为空");
        } else {
            for (int i = 0; i < fruitList.size(); i++) {
                Fruit fruit = fruitList.get(i);
                System.out.println(fruit);
            }
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void addFruit() {
        System.out.println("请输入水果名称");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByFname(fname);
        if (fruit == null) {
            System.out.println("请输入水果单价: ");
            int price = input.nextInt();
            System.out.println("请输入水果库存量: ");
            int count = input.nextInt();
            System.out.println("请输入备注: ");
            String remark = input.next();

            fruit = new Fruit(fname, price, count, remark);
            fruitDAO.addFruit(fruit);
        } else {
            System.out.println("请输入追加的库存量");
            int count = input.nextInt();
            fruit.setFcount(fruit.getFcount() + count);
            fruitDAO.updateFruit(fruit);
        }
        System.out.println("添加成功");
    }

    // 查看指定水果库存信息
    public void showFruitInfo() {
        System.out.println("请输入水果的名称: ");
        String fname = input.next();
        if (fname == null) {
            System.out.println("你他喵的在整蛊？");
        } else {
            System.out.println("---------------------------------------------------");
            System.out.println("编号\t\t名称\t\t单价\t\t库存\t\t备注");
            Fruit fruit = fruitDAO.getFruitByFname(fname);
            System.out.println(fruit);
            System.out.println("-----------------------------------------------------");
        }
    }


    // 水果下架
    public void delFruit() {
        System.out.println("请输入水果名称: ");
        String fname = input.next();
        if (fname == null) {
            System.out.println("整蛊是吧整蛊");
        } else {
            System.out.println("是否确认下架? (Y/N)");
            String stl = input.next();
            if ("y".equalsIgnoreCase(stl.trim())) {
                fruitDAO.delFruit(fname);
                System.out.println("下架成功");
            }
        }
    }

    // 退出
    public boolean exit() {
        System.out.println("是否确认退出? (Y/N)");
        String slt = input.next();
        return "y".equalsIgnoreCase(slt.trim());
    }

}
