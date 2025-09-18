package com.proj.manio.mapper;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CategoryMapper {
    @Select("SELECT * FROM category ORDER BY sort")
    List<Category> list();

    @Insert("INSERT INTO category(name,image_url,parent_id,sort,status,create_time) VALUES (#{name},#{imageUrl},#{parentId},#{sort},#{status},#{createTime})")
    void createCategory(Category category);

    @Update("UPDATE category SET name = #{name},image_url = #{imageUrl},parent_id=#{parentId},sort=#{sort},status=#{status} WHERE id = #{id}")
    void updateCategory(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategory(Integer id);

    @Select("SELECT * FROM category WHERE status = 1 ORDER BY sort")
    List<CategoryVO> listEnable();
}
