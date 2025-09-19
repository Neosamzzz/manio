package com.proj.manio.service;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.pojo.Cart;

import java.util.List;

public interface CartService {
    List<CartProduct> getCartById();

    void addProductToCart(Cart cart);

    void deleteCartByid(int id);
}
