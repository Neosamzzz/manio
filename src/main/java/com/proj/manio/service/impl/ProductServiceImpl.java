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

        PageHelper.startPage(pageNum,10);
        List<Product> products = productMapper.getProduct(categoryId);

        return new PageInfo<>(products);
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        // redis更新
        stringRedisTemplate.delete("ProductList:categoryId:"+product.getCategoryId());
        product.setCreateTime(LocalDateTime.now());
        productMapper.addProduct(product);
    }

    @Override
    public Product getById(Integer id) {
        Product product = productMapper.getById(id);
        return product;
    }


    @Transactional
    @Override
    public void updateProduct(Product product) {
        stringRedisTemplate.delete("Detail_ProductId:"+product.getId());
        productMapper.updateProduct(product);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Product product = productMapper.getById(id);
        stringRedisTemplate.delete("Detail_ProductId:"+product.getId());
        stringRedisTemplate.delete("ProductList:categoryId:"+product.getCategoryId());
        productMapper.deleteById(id);
    }

    @Override
    public void addDetailImage(Image img) {
        stringRedisTemplate.delete("Detail_ProductId:"+img.getProductId());
        productMapper.addDetailImage(img);
    }

    @Override
    public void updateDetailImage(Image img) {
        stringRedisTemplate.delete("Detail_ProductId:"+img.getProductId());
        productMapper.updateDetailImage(img);
    }

    @Transactional
    @Override
    public void deleteImage(Image img) {
        stringRedisTemplate.delete("Detail_ProductId:"+img.getProductId());
        productMapper.deleteImageById(img.getId());

    }

    @Override
    public List<Image> getImgById(Integer id) {
        return productMapper.getImgById(id);
    }
}
