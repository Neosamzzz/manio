package com.proj.manio.service;

import com.proj.manio.pojo.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> list();

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Integer id);
}
