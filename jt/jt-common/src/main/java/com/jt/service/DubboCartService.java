package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

public interface DubboCartService {
    List<Cart> findCartListByUserId(long userId);

    void addCart(Cart cart);

    void updateNum(Cart cart);

    void deleteCart(Cart cart);
}
