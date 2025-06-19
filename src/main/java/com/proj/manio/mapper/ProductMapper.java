package com.proj.manio.mapper;

import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.pojo.Image;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getProduct(int categoryId);

    @Insert("INSERT INTO product(name, description, price, category_id ,sort ,cover_img, status) VALUES (#{name}, #{description}, #{price}, #{categoryId}, #{sort}, #{coverImg}, #{status})")
    void addProduct(Product product);

    @Select("SELECT id,name,description,price,category_id,sort,cover_img,status FROM product WHERE id=#{id}")
    Product getById(Integer id);

    @Update("UPDATE product SET name=#{name}, description=#{description}, price=#{price}, category_id=#{categoryId}, sort=#{sort}, cover_img=#{coverImg}, status=#{status} WHERE id=#{id}")
    void updateProduct(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT INTO image (product_id,img_url,sort) VALUES (#{productId},#{imgUrl},#{sort})")
    void addDetailImage(Image img);

    @Update("UPDATE image SET img_url=#{imgUrl},sort=#{sort} WHERE product_id = #{productId}")
    void updateDetailImage(Image img);

    @Delete("DELETE FROM image WHERE id = #{id}")
    void deleteImageById(int id);

    @Select("SELECT * FROM image WHERE product_id = #{id} ORDER BY sort")
    List<Image> getImgById(Integer id);
}
