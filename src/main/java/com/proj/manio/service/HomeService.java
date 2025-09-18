package com.proj.manio.service;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.HomepageVO;

import java.util.List;

public interface HomeService {
    List<CategoryVO> getCategoryVO();

    List<HomepageVO> getHome();
}
