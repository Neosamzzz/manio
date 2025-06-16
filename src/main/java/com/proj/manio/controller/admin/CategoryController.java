package com.proj.manio.controller.admin;


import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="分类")
@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Operation(summary = "获取分类列表")
    @GetMapping("/list")
    public Result<List<Category>> listCategory(){
        return Result.success(categoryServiceImpl.listEnable());
    }
}
