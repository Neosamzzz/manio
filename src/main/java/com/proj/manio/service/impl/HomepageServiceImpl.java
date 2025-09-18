package com.proj.manio.service.impl;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.ProductVO;
import com.proj.manio.mapper.CategoryMapper;
import com.proj.manio.mapper.HomepageMapper;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Homepage;
import com.proj.manio.pojo.Product;
import com.proj.manio.service.HomepageService;
import com.proj.manio.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class HomepageServiceImpl implements HomepageService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private HomepageMapper homepageMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<CategoryVO> getAllCategory() {
        String json = stringRedisTemplate.opsForValue().get("catogoryVO:");
        if(json!=null && !json.isEmpty()){
            return JsonUtil.toList(json, CategoryVO.class);
        }

        List<CategoryVO> categoryVO = categoryMapper.listEnable();
        stringRedisTemplate.opsForValue().set("categoryVO:",JsonUtil.toJson(categoryVO));
        return categoryVO;

    }

    @Override
    public List<ProductVO> getProductByCId(Integer id) {

        String json = stringRedisTemplate.opsForValue().get("Manage:ProductVO:");
        if(json!=null && !json.isEmpty()){
            return JsonUtil.toList(json, ProductVO.class);
        }

        List<ProductVO> productVO = homepageMapper.getProductByCIdEnabled(id);
        stringRedisTemplate.opsForValue().set("ProductVO:",JsonUtil.toJson(productVO),1, TimeUnit.MINUTES);
        return productVO;
    }

    @Transactional
    @Override
    public void updateHomepage(List<Homepage> homepage) {
        homepageMapper.delete();
        stringRedisTemplate.delete("home:");
        for(Homepage h : homepage){
            homepageMapper.updateHomepage(h);
        }
    }

    @Override
    public List<Homepage> getHomepage() {
        return homepageMapper.getAll();
    }
}
