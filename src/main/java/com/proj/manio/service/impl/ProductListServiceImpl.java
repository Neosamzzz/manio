package com.proj.manio.service.impl;

import com.proj.manio.VO.ProductListVO;
import com.proj.manio.mapper.ProductListMapper;
import com.proj.manio.service.ProductListService;
import com.proj.manio.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ProductListServiceImpl implements ProductListService {

    @Autowired
    private ProductListMapper productListMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public List<ProductListVO> getByCategoryId(int categoryId) {
        String json = stringRedisTemplate.opsForValue().get("ProductList:categoryId:"+categoryId);
        if(json!=null){
            return JsonUtil.toList(json,ProductListVO.class);
        }
        List<ProductListVO> productListVO = productListMapper.getByCategoryId(categoryId);
        if(productListVO==null||productListVO.isEmpty()){
            stringRedisTemplate.opsForValue().set("ProductList:categoryId:"+categoryId,"[]",1,TimeUnit.MINUTES);
        }
        stringRedisTemplate.opsForValue().set("ProductList:categoryId:"+categoryId,JsonUtil.toJson(productListVO),1,TimeUnit.MINUTES);
        return productListVO;

    }
}
