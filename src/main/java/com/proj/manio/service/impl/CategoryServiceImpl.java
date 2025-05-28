package com.proj.manio.service.impl;

import com.proj.manio.mapper.CategoryMapper;
import com.proj.manio.pojo.Category;
import com.proj.manio.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listEnable() {
        return categoryMapper.listEnable();
    }


}
