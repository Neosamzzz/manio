package com.proj.manio.service.impl;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.mapper.CartMapper;
import com.proj.manio.pojo.Cart;
import com.proj.manio.service.CartService;
import com.proj.manio.util.JsonUtil;
import com.proj.manio.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<CartProduct> getCartById() {
        int id = UserHolder.get();
        String json = stringRedisTemplate.opsForValue().get("Cart:User:"+id);
        List<CartProduct> cart;
        if(json!=null && !json.isEmpty()){
            cart = JsonUtil.toList(json,CartProduct.class);
            return cart;
        }
        cart = cartMapper.getCartById(id);
        stringRedisTemplate.opsForValue().set("Cart:User:"+id,JsonUtil.toJson(cart));
        return cart;
    }

    @Override
    public void addProductToCart(Cart cart) {
        stringRedisTemplate.delete("Cart:User:"+cart.getUserId());
        cart.setCreateTime(LocalDateTime.now());
        cartMapper.addProductToCart(cart);
    }

    @Override
    public void deleteCartByid(int id) {
        stringRedisTemplate.delete("Cart:User:"+UserHolder.get());
        cartMapper.deleteCartById(id);
    }
}
