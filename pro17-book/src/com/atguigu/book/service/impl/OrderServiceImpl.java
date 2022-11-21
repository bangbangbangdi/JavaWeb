package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartItemDAO;
import com.atguigu.book.dao.OrderDAO;
import com.atguigu.book.dao.OrderItemDAO;
import com.atguigu.book.pojo.*;
import com.atguigu.book.service.CartItemService;
import com.atguigu.book.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private CartItemDAO cartItemDAO;
    private OrderItemDAO orderItemDAO;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        // service层的方法是与实际业务场景高度挂钩的,不能仅仅从方法名进行理解 例如这个简单的add方法,就需要关联其他表的增删改
        // 1.order表添加一条记录
        orderDAO.addOrderBean(orderBean);
        // 2.order_item表添加与用户购物车数量相当的记录（这里我们选择简单的实现购物车清空的功能）
        //   2.1 通过orderBean锁定对应的User
        User orderUser = orderBean.getOrderUser();
        //   2.2 通过User锁定对应的CartItem
        Map<Integer, CartItem> cartItemMap = orderUser.getCart().getCartItemMap();
        Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderBean(orderBean);
            orderItem.setBook(entry.getValue().getBook());
            orderItem.setBuyCount(entry.getValue().getBuyCount());
            orderItemDAO.addOrderItem(orderItem);
        }
        // 3.cart_item删除与之相关的记录
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }

//        List<CartItem> cartItemList = cartItemDAO.getCartItemList(orderUser);
//        for (CartItem cartItem : cartItemList) {
//            cartItemDAO.delCartItem(cartItem);
//        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);
        // 对于pojo中存在但是DB中没有的属性，要进行额外的赋值
        for (OrderBean orderBean : orderList) {
            Integer totalBookCount = orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }
        return orderList;
    }
}
