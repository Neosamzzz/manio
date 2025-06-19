package com.proj.manio.service.impl;

import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.mapper.ProductDetailMapper;
import com.proj.manio.pojo.Product;
import com.proj.manio.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public ProductDetailVO getById(Integer id) {
        ProductDetailVO productDetailVO = productDetailMapper.getById(id);
        productDetailVO.setImgs(productDetailMapper.getImgsById(id));
        return productDetailVO;
    }
}
