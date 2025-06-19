package com.proj.manio.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.mapper.ProductMapper;
import com.proj.manio.pojo.Image;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
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
    public PageInfo<Product> getProduct(int pageNum,int categoryId) {
        PageHelper.startPage(pageNum,10);
        List<Product> products = productMapper.getProduct(categoryId);

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
