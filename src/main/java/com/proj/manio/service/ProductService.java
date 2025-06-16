package com.proj.manio.service;

import com.github.pagehelper.PageInfo;
import com.proj.manio.pojo.Product;

import java.util.List;

public interface ProductService {
    public PageInfo<Product> getProduct(int pageNum);

    void addProduct(Product product);

    public Product getById(Integer id);

    void updateProduct(Product product);

    void deleteById(Integer id);
}
