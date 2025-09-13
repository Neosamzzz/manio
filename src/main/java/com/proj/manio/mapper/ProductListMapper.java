package com.proj.manio.mapper;


import com.proj.manio.VO.ProductListVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductListMapper {
    @Select("SELECT id AS productId, name, price, sort, cover_img AS coverImg FROM product WHERE category_id = #{categoryId} ORDER BY sort")
    List<ProductListVO> getByCategoryId(int categoryId);
}
