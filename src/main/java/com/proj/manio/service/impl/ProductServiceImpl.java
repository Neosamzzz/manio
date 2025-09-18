package com.proj.manio.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.mapper.ProductMapper;
import com.proj.manio.pojo.Image;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.ProductService;
import com.proj.manio.util.JsonUtil;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProductListServiceImpl productListServiceImpl;

    @Override
    @Transactional
    public PageInfo<Product> getProduct(int pageNum,int categoryId) {
//        // 判断redis有没有更改
//        if(stringRedisTemplate.opsForValue().get("productChanged：categoryId:"+categoryId) != null){
//            stringRedisTemplate.delete("Manage:Category_Page:"+categoryId+"_"+String.valueOf(pageNum));
//        }


//        String json = stringRedisTemplate.opsForValue().get("Manage:Category_Page:"+String.valueOf(categoryId)+"_"+String.valueOf(pageNum));
//        if(json!=null){
//            return new PageInfo<>(JsonUtil.toList(json,Product.class));
//        }
        PageHelper.startPage(pageNum,10);
        List<Product> products = productMapper.getProduct(categoryId);
//        if (products==null|| products.isEmpty()){//为空时存空缓存
//            stringRedisTemplate.opsForValue().set("Manage:Category_Page:"+categoryId+"_"+String.valueOf(pageNum),"[]",30,TimeUnit.MINUTES);
//            return new PageInfo<>(Collections.emptyList());
//        }
//        stringRedisTemplate.opsForValue().set("Manage:Category_Page:"+categoryId+"_"+pageNum,JsonUtil.toJson(products),30,TimeUnit.MINUTES);
        return new PageInfo<>(products);
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        product.setCreateTime(LocalDateTime.now());
        productMapper.addProduct(product);
        stringRedisTemplate.opsForValue().set("productChanged：categoryId： "+product.getCategoryId(),String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public Product getById(Integer id) {
        Product product = productMapper.getById(id);
        return product;
    }


    @Transactional
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
        stringRedisTemplate.opsForValue().set("productChanged：categoryId： "+product.getCategoryId(),String.valueOf(System.currentTimeMillis()));
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Product product = productMapper.getById(id);
        productMapper.deleteById(id);
        stringRedisTemplate.opsForValue().set("productChanged：categoryId： "+product.getCategoryId(),String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void addDetailImage(Image img) {
        productMapper.addDetailImage(img);
    }

    @Override
    public void updateDetailImage(Image img) {
        productMapper.updateDetailImage(img);
    }

    @Transactional
    @Override
    public void deleteImageById(int id) {
        productMapper.deleteImageById(id);

    }

    @Override
    public List<Image> getImgById(Integer id) {
        return productMapper.getImgById(id);
    }
}
