package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    // 获取指定用户的所有购物车项列表,
    List<CartItem> getCartItemList(User user);

    // 加载特定用户的购物车信息
    Cart getCart(User user);
}
