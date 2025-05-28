package com.proj.manio.mapper;

import com.proj.manio.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CategoryMapper {
    @Select("SELECT * FROM category WHERE status = 1 ORDER BY sort")
    List<Category> listEnable();
}
