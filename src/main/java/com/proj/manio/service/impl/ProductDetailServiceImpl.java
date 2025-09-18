package com.proj.manio.service.impl;

import com.proj.manio.VO.ProductDetailVO;
import com.proj.manio.exception.ProductNotExistException;
import com.proj.manio.mapper.ProductDetailMapper;
import com.proj.manio.pojo.Product;
import com.proj.manio.service.ProductDetailService;
import com.proj.manio.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailMapper productDetailMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ProductDetailVO getById(Integer id) {
        String json = stringRedisTemplate.opsForValue().get("Detail_ProductId:"+String.valueOf(id));
        if(json!=null){
            if ("{}".equals(json)) {
                // 缓存里标记了“空对象”，直接抛异常
                throw new ProductNotExistException();
            }
            return JsonUtil.fromJson(json, ProductDetailVO.class);
        }
        ProductDetailVO productDetailVO = productDetailMapper.getById(id);

        productDetailVO.setProductId(id);
        if(productDetailVO==null||productDetailVO.getStatus()!=1){
            stringRedisTemplate.opsForValue().set("Detail_ProductId:"+String.valueOf(id),"{}",1, TimeUnit.MINUTES);
            throw new ProductNotExistException();
        }

        productDetailVO.setImgs(productDetailMapper.getImgsById(id));
        stringRedisTemplate.opsForValue().set("Detail_ProductId:"+String.valueOf(id), JsonUtil.toJson(productDetailVO),30, TimeUnit.MINUTES);
        return productDetailVO;
    }
}
