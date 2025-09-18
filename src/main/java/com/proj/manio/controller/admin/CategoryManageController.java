package com.proj.manio.controller.admin;


import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="分类")
@RestController
@RequestMapping("/manage/Category")
public class CategoryManageController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Operation(summary = "获取分类列表")
    @GetMapping("/list")
    public Result<List<Category>> listCategory(){
        return Result.success(categoryServiceImpl.listEnable());
    }

    @Operation(summary = "新增商品类别")
    @PostMapping
    public Result<Void> createCategory(@RequestBody Category category){
        categoryServiceImpl.createCategory(category);
        return Result.success();
    }

    @Operation(summary = "更新商品类别")
    @PutMapping
    public Result<Void> updateCategory(@RequestBody Category category){
        categoryServiceImpl.updateCategory(category);
        return Result.success();
    }

    @Operation
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(@PathVariable Integer id){
        categoryServiceImpl.deleteCategory(id);
        return Result.success();
    }
}

