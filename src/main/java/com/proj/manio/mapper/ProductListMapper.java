package com.proj.manio.mapper;


import com.proj.manio.VO.ProductListVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductListMapper {
    @Select("SELECT * FROM product WHERE category_id = #{categoryId}")
    List<ProductListVO> getByCategoryId(int categoryId);
}
