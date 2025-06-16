
package com.proj.manio.controller.user;

import com.proj.manio.VO.CartProduct;
import com.proj.manio.pojo.Cart;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "购物车管理")
public class CartController {
    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping
    @Operation(summary = "获取购物车列表")
    public Result<List<CartProduct>> getCartByUserID(){
        List<CartProduct> cart = cartServiceImpl.getCartById();
        return Result.success(cart);
    }

    @PostMapping
    @Operation(summary = "添加到购物车",description = "前端设置好userId")
    public Result<Void> addProductToCart (@RequestBody Cart cart){
        cartServiceImpl.addProductToCart(cart);
        return Result.success();
    }

    @PutMapping
    @Operation(summary = "修改购物车某个商品数量")
    public Result<Void> updateProductQuantityInCart(@RequestBody Cart cart){
        cartServiceImpl.updateProductQuantityInCart(cart);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "从购物车删除商品")
    public Result<Void> deleteProductInCart(@RequestParam int id){
        cartServiceImpl.deleteCartByid(id);
        return Result.success();
    }
}

