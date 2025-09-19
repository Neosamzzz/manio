package com.proj.manio.mapper;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.pojo.Cart;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CartMapper {
    @Select("SELECT c.product_id, p.price, p.name, p.cover_img FROM cart c INNER JOIN product p ON c.product_id = p.id WHERE c.user_id = #{id} ORDER BY c.create_time")
    List<CartProduct> getCartById(int id);

    @Insert("INSERT INTO cart(user_id,product_id,create_time) VALUES (#{userId},#{productId},#{createTime})")
    void addProductToCart(Cart cart);

    @Delete("DELETE FROM cart where id=#{id}")
    void deleteCartById(int id);
}
