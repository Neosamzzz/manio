package com.proj.manio.service;

import com.github.pagehelper.PageInfo;
import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.pojo.Image;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;

import java.util.List;

public interface ProductService {
    public PageInfo<Product> getProduct(int pageNum,int categoryId);

    void addProduct(Product product);

    public Product getById(Integer id);

    void updateProduct(Product product);

    void deleteById(Integer id);

    void addDetailImage(Image img);

    void updateDetailImage(Image img);

    void deleteImageById(int id);

    List<Image> getImgById(Integer id);
}
