package com.atguigu.book.pojo;

import java.util.Map;
import java.util.Set;

// Q：这里首次出现了DB中没有的POJO类，DB中没有的话它是怎么进行存储的呢？A：不用存储
// 从这个类的属性上我们也可以看出，这个类是对DB中购物车项的内容进行聚合操作返回一些总金额，总数量之类的内容
// 由此也可以看出它确实不需要存储到DB中，而是需要的时候临时创建就好了，因此很多属性也没有set方法
public class Cart {
    private Map<Integer,CartItem> cartItemMap; // 购物车中购物车项的集合，这个Map集合中的key是Book的id
    private Double totalMoney;  // 购物车的总金额
    private Integer totalCount; // 购物车中购物车项的数量
    private Integer totalBookCount; // 购物车中书本的总数量，而不是购物车的总数量

    public Cart(){
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                CartItem cartItem = entry.getValue();
                // 该方法可能会存在金额精度丢失的问题
//                totalMoney = totalMoney + cartItem.getBook().getPrice() * cartItem.getBuyCount();
                totalMoney += cartItem.getXj();
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0){
            for (CartItem value : cartItemMap.values()) {
                totalBookCount += value.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
