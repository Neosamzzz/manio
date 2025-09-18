package com.proj.manio.mapper;

import com.proj.manio.VO.ProductVO;
import com.proj.manio.pojo.Homepage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HomepageMapper {
    @Select("SELECT * FROM product WHERE status = 1 AND category_id = #{id}")
    List<ProductVO> getProductByCIdEnabled(Integer id);

    @Delete("DELETE FROM homepage")
    void delete();

    @Insert("INSERT INTO homepage(category_id, product_id_first, product_id_second, product_id_third, product_id_fouth, sort) VALUES (#{categoryId},#{productIdFirst},#{productIdSecond},#{productIdThird},#{productIdFouth},#{sort})")
    void updateHomepage(Homepage h);

    @Select("SELECT * FROM homepage ORDER BY sort")
    List<Homepage> getAll();
}
