package com.atguigu.book.dao;

import com.atguigu.book.pojo.OrderItem;

public interface OrderItemDAO {
    // 这里只有添加而没有删除显然是不合理的
    void addOrderItem(OrderItem orderItem);
}
