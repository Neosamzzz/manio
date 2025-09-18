package com.proj.manio.service.impl;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.HomepageVO;
import com.proj.manio.mapper.CategoryMapper;
import com.proj.manio.mapper.HomeMapper;
import com.proj.manio.mapper.HomepageMapper;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Homepage;
import com.proj.manio.service.HomeService;
import com.proj.manio.service.HomepageService;
import com.proj.manio.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HomeServiceImpl implements HomeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private HomepageMapper homepageMapper;
    @Autowired
    private HomeMapper homeMapper;

    @Override
    public List<CategoryVO> getCategoryVO() {
        return categoryMapper.listEnable();
    }

    @Override
    public List<HomepageVO> getHome() {
        String json = stringRedisTemplate.opsForValue().get("home:");
        if(json!=null && !json.isEmpty()){
            return JsonUtil.toList(json, HomepageVO.class);
        }
        List<Homepage> homepageList = homepageMapper.getAll();

        List<HomepageVO> homepageVOList = new ArrayList<HomepageVO>();


        for(Homepage h: homepageList){
            HomepageVO homepageVO = new HomepageVO();
            Integer CATEGORY_ID = h.getCategoryId();
            homepageVO.setCategoryId(CATEGORY_ID);
            homepageVO.setCategoryName(homeMapper.getCategoryName(CATEGORY_ID));
            homepageVO.setImageUrl(homeMapper.getImgUrl(CATEGORY_ID));

            Integer FIRST_ID = h.getProductIdFirst();
            Integer SECOND_ID = h.getProductIdSecond();
            Integer THIRD_ID = h.getProductIdThird();
            Integer FOUTH_ID = h.getProductIdFouth();


            homepageVO.setProductIdFirst(FIRST_ID);
            homepageVO.setProductNameFirst(homeMapper.getProductName(FIRST_ID));
            homepageVO.setProductCoverImgFirst(homeMapper.getCoverImg(FIRST_ID));

            homepageVO.setProductIdSecond(FIRST_ID);
            homepageVO.setProductNameSecond(homeMapper.getProductName(SECOND_ID));
            homepageVO.setProductCoverImgFirst(homeMapper.getCoverImg(SECOND_ID));

            homepageVO.setProductIdThird(FIRST_ID);
            homepageVO.setProductNameThird(homeMapper.getProductName(THIRD_ID));
            homepageVO.setProductCoverImgThird(homeMapper.getCoverImg(THIRD_ID));

            homepageVO.setProductIdFouth(FIRST_ID);
            homepageVO.setProductNameFouth(homeMapper.getProductName(FOUTH_ID));
            homepageVO.setProductCoverImgFouth(homeMapper.getCoverImg(FOUTH_ID));

            homepageVOList.add(homepageVO);
        }
        return homepageVOList;
    }

}
