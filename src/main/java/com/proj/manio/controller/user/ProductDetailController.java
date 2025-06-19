package com.proj.manio.controller.user;

import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.ProductDetailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product-detail")
@Tag(name = "商品详细页")
public class ProductDetailController {
    @Autowired
    private ProductDetailServiceImpl productDetailService;

    @GetMapping("/{id}")
    @Operation(summary = "根据id进入商品详细页")
    public Result<ProductDetailVO> getById(@PathVariable Integer id){
        return Result.success(productDetailService.getById(id));
    }
}
