package com.proj.manio.controller.admin;


import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.pojo.Image;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.ProductServiceImpl;
import com.proj.manio.util.AliyunOSS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ProductManage")
@Tag(name="商品管理")
public class ProductManageController {
    @Autowired
    private ProductServiceImpl productServiceImpl;
    @Autowired
    private AliyunOSS aliyunOSS;

    @GetMapping
    @Operation(summary = "所有商品",description = "传入参数pageNum和categoryId")
    public Result<PageInfo<Product>> getProduct(@RequestParam(defaultValue = "1") int pageNum,@RequestParam int categoryId){
        return Result.success(productServiceImpl.getProduct(pageNum,categoryId));
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

    @GetMapping("/get-img-by-id/{id}")
    @Operation(summary = "获取当前商品的所有详细图")
    public Result<List<Image>> getImgById(@PathVariable Integer id){
        return Result.success(productServiceImpl.getImgById(id));
    }

    @PostMapping("/add-image")
    @Operation(summary = "给商品添加详细图")
    public Result<Void> addDetailImage(@RequestBody Image img){
        productServiceImpl.addDetailImage(img);
        return Result.success();
    }

    @PutMapping("/update-image")
    @Operation(summary = "更改详细图信息")
    public Result<Void> updateDetailImage(@RequestBody Image img){
        productServiceImpl.updateDetailImage(img);
        return Result.success();
    }

    @DeleteMapping("/delete-image")
    @Operation(summary = "删除略缩图")
    public Result<Void> deleteDetailImage(@RequestBody Image img) throws ClientException, IOException {
        String url = img.getImgUrl();
        aliyunOSS.remove(url.substring(url.lastIndexOf("/")+1));
        productServiceImpl.deleteImageById(img.getId());
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除")
    public Result<Void> deleteById (@PathVariable Integer id){
        productServiceImpl.deleteById(id);
        return Result.success();
    }
}
