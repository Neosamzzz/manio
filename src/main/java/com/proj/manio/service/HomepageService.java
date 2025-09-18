package com.proj.manio.service;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.ProductVO;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Homepage;

import java.util.List;

public interface HomepageService {
    List<CategoryVO> getAllCategory();

    List<ProductVO> getProductByCId(Integer id);

    void updateHomepage(List<Homepage> homepage);

    List<Homepage> getHomepage();
}
