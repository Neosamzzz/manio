package com.proj.manio.controller.user;

import com.proj.manio.VO.ProductListVO;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.ProductListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "商品列表展示")
public class ProductListController {
    @Autowired
    private ProductListServiceImpl productListService;

    @GetMapping("/product-list")
    @Operation(summary = "根据类别获取商品列表")
    public Result<List<ProductListVO>> productListVOResult(@RequestParam int categoryId){
        log.info("根据种类：{}获取商品",categoryId);
        return Result.success(productListService.getByCategoryId(categoryId));
    }
}
