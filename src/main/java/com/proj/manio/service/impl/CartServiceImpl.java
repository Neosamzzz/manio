package com.proj.manio.service.impl;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.mapper.CartMapper;
import com.proj.manio.pojo.Cart;
import com.proj.manio.service.CartService;
import com.proj.manio.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartProduct> getCartById() {
        int id = UserHolder.get();
        List<CartProduct> cart = cartMapper.getCartById(id);
        return cart;
    }

    @Override
    public void addProductToCart(Cart cart) {
        cartMapper.addProductToCart(cart);
    }

    @Override
    public void updateProductQuantityInCart(Cart cart) {
        cartMapper.updateProductQuantityInCart(cart);
    }

    @Override
    public void deleteCartByid(int id) {
        cartMapper.deleteCartById(id);
    }
}
