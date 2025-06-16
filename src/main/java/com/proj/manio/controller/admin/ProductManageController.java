package com.proj.manio.controller.admin;


import com.github.pagehelper.PageInfo;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ProductManage")
@Tag(name="商品管理")
public class ProductManageController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    @Operation(summary = "所有商品",description = "传入参数pageNum")
    public Result<PageInfo<Product>> getProduct(@RequestParam(defaultValue = "1") int pageNum){
        return Result.success(productServiceImpl.getProduct(pageNum));
    }

    @PostMapping
    @Operation(summary = "新增商品")
    public Result<Void> addProduct(@RequestBody Product product){
        productServiceImpl.addProduct(product);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询商品")
    public Result<Product> getByid(@PathVariable Integer id){
        Product getProduct = productServiceImpl.getById(id);
        return Result.success(getProduct);
    }

    @PutMapping
    @Operation(summary = "修改商品")
    public Result<Void> updateProduct(@RequestBody Product product){
        productServiceImpl.updateProduct(product);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public Result<Void> deleteById (@PathVariable Integer id){
        productServiceImpl.deleteById(id);
        return Result.success();
    }
}
