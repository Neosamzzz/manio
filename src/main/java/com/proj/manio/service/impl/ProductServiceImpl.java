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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        String json = stringRedisTemplate.opsForValue().get("Manage:Category_Page:"+String.valueOf(categoryId)+"_"+String.valueOf(pageNum));
        if(json!=null){
            return JsonUtil.fromJson(json, new TypeReference<PageInfo<Product>>() {});
        }
        PageHelper.startPage(pageNum,10);
        List<Product> products = productMapper.getProduct(categoryId);
        if (products==null|| products.isEmpty()){//为空时存空缓存
            stringRedisTemplate.opsForValue().set("Manage:Category_Page:"+categoryId+"_"+String.valueOf(pageNum),"[]",30,TimeUnit.MINUTES);
            return new PageInfo<>(Collections.emptyList());
        }
        stringRedisTemplate.opsForValue().set("Manage:Category_Page:"+categoryId+"_"+pageNum,JsonUtil.toJson(products),30,TimeUnit.MINUTES);
        return new PageInfo<>(products);
    }

    @Override
    public void addProduct(Product product) {
        product.setCreateTime(LocalDateTime.now());
        productMapper.addProduct(product);
    }

    @Override
    public Product getById(Integer id) {
        String json = stringRedisTemplate.opsForValue().get("Manage:Product" + String.valueOf(id));

        if(json!=null) {
            return JsonUtil.fromJson(json,Product.class);
        }
        Product product = productMapper.getById(id);
        if(product!=null){
            stringRedisTemplate.opsForValue().set("Manage:Product" + String.valueOf(id), JsonUtil.toJson(product), 60, TimeUnit.SECONDS);
        }
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public void addDetailImage(Image img) {
        productMapper.addDetailImage(img);
    }

    @Override
    public void updateDetailImage(Image img) {
        productMapper.updateDetailImage(img);
    }

    @Override
    public void deleteImageById(int id) {
        productMapper.deleteImageById(id);
    }

    @Override
    public List<Image> getImgById(Integer id) {
        return productMapper.getImgById(id);
    }
}
