package com.proj.manio.mapper;

import org.apache.ibatis.annotations.Select;

public interface HomeMapper {
    @Select("SELECT name FROM category WHERE id = #{categoryId}")
    String getCategoryName(Integer categoryId);

    @Select("SELECT image_url FROM category WHERE id = #{categoryId}")
    String getImgUrl(Integer categoryId);

    @Select("SELECT name FROM product WHERE id = #{firstId}")
    String getProductName(Integer firstId);

    @Select("SELECT cover_img FROM product WHERE id = #{firstId}")
    String getCoverImg(Integer firstId);
}
