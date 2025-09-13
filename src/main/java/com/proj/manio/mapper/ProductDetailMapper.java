package com.proj.manio.mapper;

import com.proj.manio.VO.ProductDetailVO;
import org.apache.ibatis.annotations.Select;

public interface ProductDetailMapper {

    @Select("SELECT name,price,description,status FROM product WHERE id = #{id}")
    ProductDetailVO getById(Integer id);

    @Select("SELECT img_url FROM image WHERE product_id = #{id} ORDER BY sort")
    String[] getImgsById(Integer id);
}
