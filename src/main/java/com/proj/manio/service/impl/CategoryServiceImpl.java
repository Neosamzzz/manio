package com.proj.manio.service.impl;

import com.github.pagehelper.PageInfo;
import com.proj.manio.mapper.CategoryMapper;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Product;
import com.proj.manio.service.CategoryService;
import com.proj.manio.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<Category> listEnable() {
        return categoryMapper.listEnable();
    }

    @Override
    public void createCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }


}
