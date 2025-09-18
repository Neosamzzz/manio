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
import java.util.concurrent.TimeUnit;

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
        String json = stringRedisTemplate.opsForValue().get("categoryVO:");
        if(json!=null && !json.isEmpty()){
            return JsonUtil.toList(json, CategoryVO.class);
        }
        return categoryMapper.listEnable();
    }

    @Override
    public List<HomepageVO> getHome() {
        String cacheKey = "home:";
        String lockKey = "lock:home";

        // 1. 先查缓存
        String json = stringRedisTemplate.opsForValue().get(cacheKey);
        if (json != null && !json.isEmpty()) {
            return JsonUtil.toList(json, HomepageVO.class);
        }

        try {
            // 2. 尝试获取互斥锁（过期时间10秒）
            Boolean success = stringRedisTemplate.opsForValue()
                    .setIfAbsent(lockKey, "1", 10, TimeUnit.SECONDS);

            if (Boolean.TRUE.equals(success)) {
                // 抢到锁，查数据库
                List<Homepage> homepageList = homepageMapper.getAll();
                List<HomepageVO> homepageVOList = new ArrayList<>();

                for (Homepage h : homepageList) {
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

                    homepageVO.setProductIdSecond(SECOND_ID);
                    homepageVO.setProductNameSecond(homeMapper.getProductName(SECOND_ID));
                    homepageVO.setProductCoverImgSecond(homeMapper.getCoverImg(SECOND_ID));

                    homepageVO.setProductIdThird(THIRD_ID);
                    homepageVO.setProductNameThird(homeMapper.getProductName(THIRD_ID));
                    homepageVO.setProductCoverImgThird(homeMapper.getCoverImg(THIRD_ID));

                    homepageVO.setProductIdFouth(FOUTH_ID);
                    homepageVO.setProductNameFouth(homeMapper.getProductName(FOUTH_ID));
                    homepageVO.setProductCoverImgFouth(homeMapper.getCoverImg(FOUTH_ID));

                    homepageVOList.add(homepageVO);
                }

                // 3. 存入缓存
                stringRedisTemplate.opsForValue()
                        .set(cacheKey, JsonUtil.toJson(homepageVOList));
                return homepageVOList;
            } else {
                // 没拿到锁，稍微等一会儿再查缓存（避免打爆数据库）
                Thread.sleep(5000);
                String retryJson = stringRedisTemplate.opsForValue().get(cacheKey);
                if (retryJson != null && !retryJson.isEmpty()) {
                    return JsonUtil.toList(retryJson, HomepageVO.class);
                }
                // 如果还没有，只能兜底去数据库
                return homepageMapper.getAll().stream().map(h -> {
                    HomepageVO vo = new HomepageVO();
                    vo.setCategoryId(h.getCategoryId());
                    vo.setCategoryName(homeMapper.getCategoryName(h.getCategoryId()));
                    vo.setImageUrl(homeMapper.getImgUrl(h.getCategoryId()));
                    return vo;
                }).toList();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 释放锁
            stringRedisTemplate.delete(lockKey);
        }
    }

}
