package com.atguigu.fruit.view;

import com.atguigu.fruit.controller.Menu;

public class Client {

    public static void main(String[] args) {
        Menu m = new Menu();

        boolean flag = true;
        while (flag) {
            int slt = m.showMainMenu();
            switch (slt) {
                // 显示库存列表
                case 1:
                    m.showFruitList();
                    break;
                // 添加
                case 2:
                    m.addFruit();
                    break;
                // 显示指定水果的信息
                case 3:
                    m.showFruitInfo();
                    break;
                // 删除指定水果
                case 4:
                    m.delFruit();
                    break;
                // 退出
                case 5:
                    flag = !m.exit();
                    break;
                default:
                    System.out.println("别整蛊了...");
                    break;
            }
        }
        System.out.println("谢谢");
    }

}