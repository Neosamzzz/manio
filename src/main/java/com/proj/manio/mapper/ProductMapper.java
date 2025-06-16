package com.proj.manio.mapper;

import com.proj.manio.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {
    @Select("SELECT * FROM product ORDER BY sort DESC")
    List<Product> getProduct();

    @Insert("INSERT INTO product(name, description, price, category_id ,sort ,cover_img, status) VALUES (#{name}, #{description}, #{price}, #{categoryId}, #{sort}, #{coverImg}, #{status})")
    void addProduct(Product product);

    @Select("SELECT id,name,description,price,category_id,sort,cover_img,status FROM product WHERE id=#{id}")
    Product getById(Integer id);

    @Update("UPDATE product SET name=#{name}, description=#{description}, price=#{price}, category_id=#{categoryId}, sort=#{sort}, cover_img=#{coverImg}, status=#{status} WHERE id=#{id}")
    void updateProduct(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteById(Integer id);
}
