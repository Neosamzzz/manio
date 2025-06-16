package com.proj.manio.mapper;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.pojo.Cart;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CartMapper {
    @Select("SELECT product_id,price,name,cover_img,quantity FROM cart c LEFT JOIN product p ON c.user_id = #{id} AND product_id = p.id")
    List<CartProduct> getCartById(int id);

    @Insert("INSERT INTO cart(user_id,product_id,quantity) VALUES (#{userId},#{productId},#{quantity})")
    void addProductToCart(Cart cart);

    @Update("UPDATE cart SET quantity=#{quantity} WHERE user_id=#{userId} AND id=#{id}")
    void updateProductQuantityInCart(Cart cart);

    @Delete("DELETE FROM cart where id=#{id}")
    void deleteCartById(int id);
}
