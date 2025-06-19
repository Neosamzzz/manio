package com.proj.manio.service.impl;

import com.proj.manio.VO.ProductListVO;
import com.proj.manio.mapper.ProductListMapper;
import com.proj.manio.service.ProductListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductListServiceImpl implements ProductListService {

    @Autowired
    private ProductListMapper productListMapper;
    @Override
    public List<ProductListVO> getByCategoryId(int categoryId) {
        return productListMapper.getByCategoryId(categoryId);
    }
}
