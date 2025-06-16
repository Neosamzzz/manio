package com.proj.manio.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.proj.manio.mapper.ProductMapper;
import com.proj.manio.pojo.Product;
import com.proj.manio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public PageInfo<Product> getProduct(int pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Product> products = productMapper.getProduct();

        return new PageInfo<>(products);
    }

    @Override
    public void addProduct(Product product) {
        product.setCreateTime(LocalDateTime.now());
        productMapper.addProduct(product);
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }
}
