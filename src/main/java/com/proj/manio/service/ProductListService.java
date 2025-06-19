package com.proj.manio.service;

import com.proj.manio.VO.ProductListVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductListService {

    List<ProductListVO> getByCategoryId(int categoryId);
}
